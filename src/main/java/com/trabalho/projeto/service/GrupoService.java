package com.trabalho.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.GrupoUsuarioDto;
import com.trabalho.projeto.exception.DataIntegrityViolationException;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.GrupoRepository;

@Service
public class GrupoService {
    
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo adicionarGrupo(String nomeGrupo) {
        if (buscarGrupoPorNome(nomeGrupo) != null)
            throw new DataIntegrityViolationException("Nome de grupo \"" + nomeGrupo + "\" já existe!");

        Grupo grupo = new Grupo(nomeGrupo);
        return grupoRepository.save(grupo);
    }

    public Grupo editarGrupo(Grupo grupoEditado) {
        Grupo grupo = buscarGrupoPorId(grupoEditado.getId());

        if (!grupoEditado.getNome().isBlank()) {
            grupo.setNome(grupoEditado.getNome());
            grupoRepository.save(grupo);
        }

        return grupo;
    }

    public Grupo buscarGrupoPorNome(String nomeGrupo) {
        usuarioService.verificarUsuarioLogado();
        return grupoRepository.findOneByNome(nomeGrupo);
    }

    public Grupo buscarGrupoPorId(int idGrupo) {
        usuarioService.verificarUsuarioLogado();
        return grupoRepository
            .findById(idGrupo)
            .orElseThrow(
                () -> new com.trabalho.projeto.exception.NoSuchElementException("Grupo " + idGrupo +" não encontrado!")
            );
    }

    public void deletarGrupo(int id) {
        usuarioService.verificarUsuarioLogado();
        Grupo grupo = buscarGrupoPorId(id);
        grupoRepository.delete(grupo);
    }

    public List<Grupo> listarGrupos() {
        usuarioService.verificarUsuarioLogado();
        return grupoRepository.findAll();
    }

    public void vincularUsuario(GrupoUsuarioDto grupoUsuarioDto) {
        usuarioService.verificarUsuarioLogado();
        Grupo grupo = buscarGrupoPorId(grupoUsuarioDto.getIdGrupo());
        Usuario usuario = usuarioService.buscarUsuario(grupoUsuarioDto.getIdUsuario());

        grupo.adicionarUsuario(usuario);
        grupoRepository.save(grupo);
    }

    public List<Usuario> listarUsuarios(int idGrupo) {
        usuarioService.verificarUsuarioLogado();
        return usuarioService.buscarUsuarioPorGrupo(idGrupo);
    }

    public List<Grupo> buscarGrupoPorSetor(int idSetor) {
        return grupoRepository.findGrupoBySetorGrupos_IdSetor(idSetor);
    }
}
