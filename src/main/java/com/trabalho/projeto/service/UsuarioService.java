package com.trabalho.projeto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.UsuarioDto;
import com.trabalho.projeto.dto.UsuarioEditadoDto;
import com.trabalho.projeto.exception.LoginException;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.TarefasRepository;
import com.trabalho.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefasRepository tarefasRepository;

    public Usuario adicionarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = converterDtoEmEntidade(usuarioDto);
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(int id) {
        Usuario usuario = buscarUsuario(id);
        List<Tarefas> tarefasExclusao = new ArrayList<>();
        List<Tarefas> tarefasAlteradas = new ArrayList<>();

        for (Tarefas tarefa : usuario.getTarefas()) {
            if (tarefa.getUsuarios().size() > 1) {
                tarefa.removerUsuario(usuario);
                tarefasAlteradas.add(tarefa);
            }
            else {
                tarefasExclusao.add(tarefa);
            }
        }

        tarefasRepository.saveAll(tarefasAlteradas);
        tarefasRepository.deleteAll(tarefasExclusao);

        usuarioRepository.delete(usuario);
    }

    private Usuario converterDtoEmEntidade(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto);
        return usuario;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(int id) {
        return usuarioRepository
            .findById(id)
            .orElseThrow(
                () -> new com.trabalho.projeto.exception.NoSuchElementException("Usuário "+ id +" não encontrado!")
        );
    }

    public Usuario editarUsuario(UsuarioEditadoDto usuarioEditadoDto) {
        Usuario usuario = buscarUsuario(usuarioEditadoDto.getIdUsuario());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(usuarioEditadoDto.getSenhaAtual(), usuario.getSenha()))
            throw new LoginException("Senha atual incorreta!");

        boolean temAlteracao = false;

        if (!usuarioEditadoDto.getNome().isBlank()) {
            usuario.setNome(usuarioEditadoDto.getNome());
            temAlteracao = true;
        }

        if (!usuarioEditadoDto.getEmail().isBlank()) {
            usuario.setEmail(usuarioEditadoDto.getEmail());
            temAlteracao = true;
        }

        if (!usuarioEditadoDto.getSenhaNova().isBlank()){
            usuario.definirSenhaEncriptada(usuarioEditadoDto.getSenhaNova());
            temAlteracao = true;
        }

        if (temAlteracao)
            usuarioRepository.save(usuario);

        return usuario;
    }

    public void verificarUsuarioLogado() {
        Usuario usuarioLogado = buscarUsuarioLogado();
        
        if (usuarioLogado == null)
            throw new LoginException("Nenhum usuário logado encontrado!");

    }

    public List<Usuario> buscarUsuarioPorGrupo(int idGrupo) {
        return usuarioRepository.findUsuarioByGrupoUsuario_Id(idGrupo);
    }

    public Usuario buscarUsuarioLogado() {
        return usuarioRepository.findOneByIsLogadoTrue();
    }
}
