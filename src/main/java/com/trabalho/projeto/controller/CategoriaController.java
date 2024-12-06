package com.trabalho.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.GrupoCategoriaDto;
import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.service.CategoriaService;

/**
* Controlador para categorias
*/
@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    /**
     * Adicionar categoria
     * @param categoriaNova Insira o nome da nova categoria:
     */
    @PostMapping
    public ResponseEntity<Categoria> adicionarCategoria(@RequestParam String categoriaNova) {
        Categoria categoria = categoriaService.adicionarCategoria(categoriaNova);
        return ResponseEntity.ok().body(categoria);
    }

    /**
     * Visualizar todas as categorias cadastradas
     */
    @GetMapping()
    public ResponseEntity<List<Categoria>> vizualizarCategorias() {
        List<Categoria> categorias = categoriaService.vizualizarCategorias();

        return ResponseEntity.ok().body(categorias);
    }

    /**
     * Editar categoria
     * @param categoriaEditada Editor para modificar uma categoria:
     */
    @PutMapping()
    public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoriaEditada) {
        Categoria categoria = categoriaService.editarCategoria(categoriaEditada);

        return ResponseEntity.ok().body(categoria);
    }

    /**
     * Vincular grupo
     * @param grupoCategoriaDto Editor para vincular um grupo a uma categoria:
     */
    @PutMapping("vincular-grupo")
    public ResponseEntity<MensagemDTO> vincularGrupo(@RequestBody GrupoCategoriaDto grupoCategoriaDto) {
        categoriaService.vincularGrupo(grupoCategoriaDto);

        return ResponseEntity.ok().body(new MensagemDTO("OK", "Grupo \"" + grupoCategoriaDto.getIdGrupo()
                + "\" vinculado a categoria \"" + grupoCategoriaDto.getIdCategoria() + "\"."));
    }

    /**
     * Buscar categoria
     * @param id Id da categoria que deseja encontrar:
     */
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> vizualizarCategoria(@RequestParam int id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    /**
     * Deletar categoria
     * @param id Id da categoria a ser deletada:
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarCategoria(@RequestParam int id) {
        try {
            categoriaService.deletarCategoria(id);
        } catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
}