package com.trabalho.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.CategoriaDto;
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
}