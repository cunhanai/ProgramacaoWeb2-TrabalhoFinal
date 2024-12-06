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

import com.trabalho.projeto.dto.CategoriaDto;
import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.service.CategoriaService;



@RequestMapping(value = "/categoria")
@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> adicionarCategoria(@RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = categoriaService.adicionarCategoria(categoriaDto);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> vizualizarCategorias() {
        List<Categoria> categorias = categoriaService.vizualizarCategorias();

        return ResponseEntity.ok().body(categorias);
    }
    
    @PutMapping()
    public ResponseEntity<Categoria> editarCategoria(@RequestBody Categoria categoriaEditada) {
        Categoria categoria = categoriaService.editarCategoria(categoriaEditada);
        
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> vizualizarCategoria(@RequestParam int id) {
        Categoria categoria = categoriaService.buscarCategoria(id);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarCategoria(@RequestParam int id) {
        try {
            categoriaService.deletarCategoria(id);
        }
        catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
}