package com.trabalho.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.dto.TarefasCategoriaDto;
import com.trabalho.projeto.dto.TarefasDto;
import com.trabalho.projeto.dto.UsuarioTarefaDto;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.service.TarefasService;


/**
 * Controlador para tarefas
 */
@RestController
@RequestMapping(value = "/tarefas")
public class TarefasController {
    
    @Autowired
    TarefasService tarefasService;

    /**
     * Adicionar tarefa
     * @param tarefasDto Editor para adicionar uma tarefa:
     */
    @PostMapping
    public ResponseEntity<Tarefas> adicionarTarefa(@RequestBody TarefasDto tarefasDto) {
        Tarefas tarefas = tarefasService.adicionarTarefas(tarefasDto);
        return ResponseEntity.ok().body(tarefas);
    }

    /**
     * Editar tarefa
     * @param tarefaEditada Editor para modificar uma tarefa:
     */
    @PutMapping
    public ResponseEntity<Tarefas> editarTarefa(@RequestBody Tarefas tarefaEditada) {
        Tarefas tarefas = tarefasService.editarTarefa(tarefaEditada);

        return ResponseEntity.ok().body(tarefas);
    }

    /**
     * Buscar tarefa
     * @param id Id da tarefa que deseja encontrar:
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarefas> verTarefa(@RequestParam int id) {
        Tarefas tarefa = tarefasService.buscarTarefa(id);
        return ResponseEntity.ok().body(tarefa);
    }

    /**
     * Visualizar todas as tarefas
     */
        @GetMapping
    public ResponseEntity<List<Tarefas>> vizualizarTarefas() {
        List<Tarefas> tarefas = tarefasService.listarTarefas();
        return ResponseEntity.ok().body(tarefas);
    }

    /**
     * Deletar tarefa
     * @param id Id da tarefa a ser deletada:
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MensagemDTO> deletarTarefa (@PathVariable int id) {
        try {
            tarefasService.deletarTarefa(id);
        }
        catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }

    /**
     * Vincular categoria
     * @param tarefasCategoriaDto Editor para vincular uma categoria a uma tarefa:
     */
    @PutMapping("/vincular-categoria")
    public ResponseEntity<MensagemDTO> vincularCategoria(@RequestBody TarefasCategoriaDto tarefasCategoriaDto) {
        tarefasService.vincularCategoria(tarefasCategoriaDto);
        
        return ResponseEntity.ok().body(new MensagemDTO("OK", "Categoria \"" + tarefasCategoriaDto.getIdCategoria() + "\" vinculada a tarefa \"" + tarefasCategoriaDto.getIdTarefa() + "\"."));
    }

    /**
     * Vincular usuario
     * @param usuarioTarefas Editor para vincular um usuario a uma tarefa:
     */
    @PostMapping("/vincular-usuario")
    public ResponseEntity<UsuarioTarefaDto> vincularUsuario(@RequestBody UsuarioTarefaDto usuarioTarefas) {
        tarefasService.vincularUsuario(usuarioTarefas.getTarefaId(), usuarioTarefas.getUsuarioId());
        return ResponseEntity.ok().body(usuarioTarefas);
    }
    
    
    /**
     * Desvincular usuario
     * @param usuarioTarefas Editor para desvincular um usuario a uma tarefa:
     * @return
     */
    @PostMapping("/desvincular-usuario")
    public ResponseEntity<MensagemDTO> desvincularUsuario(@RequestBody UsuarioTarefaDto usuarioTarefas) {
        tarefasService.desvincularUsuario(usuarioTarefas.getTarefaId(), usuarioTarefas.getUsuarioId());
        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
}
