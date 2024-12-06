package com.trabalho.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.TarefasCategoriaDto;
import com.trabalho.projeto.dto.TarefasDto;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.TarefasRepository;

@Service
public class TarefasService {
    
    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefas adicionarTarefas(TarefasDto tarefasDto) {
        usuarioService.verificarUsuarioLogado();

        Tarefas tarefas =  new Tarefas(tarefasDto);
        Usuario usuario = usuarioService.buscarUsuarioLogado();

        tarefas.adicionarUsuario(usuario);

        if (tarefasDto.getIdCategoria() > 0) {
            Categoria categoria = categoriaService.buscarCategoria(tarefasDto.getIdCategoria());
            tarefas.adicionarCategoria(categoria);
        }

        return tarefasRepository.save(tarefas);
    }

    public Tarefas editarTarefa(Tarefas tarefaEditada) {
        Tarefas tarefa = buscarTarefa(tarefaEditada.getIdTarefa());

        if (!tarefaEditada.getTituloTarefa().isBlank()) {
            tarefa.setTituloTarefa(tarefaEditada.getTituloTarefa());
            tarefasRepository.save(tarefa);
        }

        return tarefa;
    }

    public Tarefas buscarTarefa(int idTarefa) {
        usuarioService.verificarUsuarioLogado();
        return tarefasRepository
            .findById(idTarefa)
            .orElseThrow(
                () -> new com.trabalho.projeto.exception.NoSuchElementException("Tarefa " + idTarefa +" não encontrado!")
            );
    }

    public void deletarTarefa(int id) {
        usuarioService.verificarUsuarioLogado();
        Tarefas tarefa = buscarTarefa(id);
        tarefasRepository.delete(tarefa);
    }

    public List<Tarefas> listarTarefas() {
        usuarioService.verificarUsuarioLogado();
        return tarefasRepository.findAll();
    }

    public void vincularCategoria(TarefasCategoriaDto tarefasCategoriaDto) {
        usuarioService.verificarUsuarioLogado();
        Tarefas tarefa = buscarTarefa(tarefasCategoriaDto.getIdTarefa());
        Categoria categoria = categoriaService.buscarCategoria(tarefasCategoriaDto.getIdCategoria());

        tarefa.adicionarCategoria(categoria);
        tarefasRepository.save(tarefa);
    }

    public void vincularUsuario(int tarefaId, int usuarioId) {
        usuarioService.verificarUsuarioLogado();

        Tarefas tarefa = buscarTarefa(tarefaId);
        Usuario usuario = usuarioService.buscarUsuario(usuarioId);

        tarefa.adicionarUsuario(usuario);
        tarefasRepository.save(tarefa);
    }

    public void desvincularUsuario(int tarefaId, int usuarioId) {
        usuarioService.verificarUsuarioLogado();
        Usuario usuario = usuarioService.buscarUsuario(usuarioId);

        Tarefas tarefa = buscarTarefa(tarefaId);

        tarefa.removerUsuario(usuario);
        tarefasRepository.save(tarefa);
    }

}
