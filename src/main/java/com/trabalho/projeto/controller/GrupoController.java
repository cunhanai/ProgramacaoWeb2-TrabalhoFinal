package com.trabalho.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.service.GrupoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trabalho.projeto.dto.GrupoUsuarioDto;
import com.trabalho.projeto.dto.MensagemDTO;



/**
 * Controlador para grupos
 */
@RestController
@RequestMapping(value = "/grupo")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    /**
     * Adicionar grupo
     * @param nomeGrupo Insira o nome do novo grupo:
     */
    @PostMapping()
    public ResponseEntity<Grupo> adicionarGrupo(@RequestParam String nomeGrupo) {
        Grupo grupo = grupoService.adicionarGrupo(nomeGrupo);
        
        return ResponseEntity.ok().body(grupo);
    }
    
    /**
     * Editar grupo
     * @param grupoEditado Editor para modificar um grupo:
     */
    @PutMapping()
    public ResponseEntity<Grupo> editarGrupo(@RequestBody Grupo grupoEditado) {
        Grupo grupo = grupoService.editarGrupo(grupoEditado);
        
        return ResponseEntity.ok().body(grupo);
    }

    /**
     * Visualizar todos os grupos cadastrados
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Grupo>> vizualizarGrupos() {
        List<Grupo> grupos = grupoService.listarGrupos();
        return ResponseEntity.ok().body(grupos);
    }

    /**
     * Deletar grupo
     * @param id Id do grupo a ser deletado:
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MensagemDTO> deletarGrupo(@PathVariable int id) {
        try {
            grupoService.deletarGrupo(id);
        }
        catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
    
    /**
     * Vincular usuario
     * @param grupoUsuarioDto Editor para vincular um usuario a um grupo: 
     */
    @PutMapping("/vincular-usuario")
    public ResponseEntity<MensagemDTO> vincularUsuario(@RequestBody GrupoUsuarioDto grupoUsuarioDto) {
        grupoService.vincularUsuario(grupoUsuarioDto);
        
        return ResponseEntity.ok().body(new MensagemDTO("OK", "Usuário \"" + grupoUsuarioDto.getIdUsuario() + "\" vinculado ao grupo \"" + grupoUsuarioDto.getIdGrupo() + "\"."));
    }

    /**
     * PRECISA CORRIGIR
     * @param idGrupo Insira o id do grupo para ver os usuarios vinculados:
     */
    @GetMapping("/{idGrupo}/usuarios") 
    public ResponseEntity<List<Usuario>> vizualizarGrupos(@PathVariable int idGrupo) {
        List<Usuario> usuarios = grupoService.listarUsuarios(idGrupo);
        return ResponseEntity.ok().body(usuarios);
    }

    /**
     * PRECISA CORRIGIR
     * @param idGrupo Insira o id do grupo para ver as categorias vinculadas:
     */
    @GetMapping("/{idGrupo}/categorias")
    public ResponseEntity<List<Categoria>> getMethodName(@PathVariable int idGrupo) {
        List<Categoria> categoria = grupoService.listarCategorias(idGrupo);
        return ResponseEntity.ok().body(categoria);
    }
    
}
