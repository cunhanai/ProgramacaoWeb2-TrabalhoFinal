package com.trabalho.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.TarefasDto;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.service.TarefasService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping(value = "/tarefas")
@RestController
public class TarefasController {
    
    @Autowired
    TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<Tarefas> adicionarTarefa(@RequestBody TarefasDto tarefasDto) {
        Tarefas tarefas = tarefasService.adicionarTarefas(tarefasDto);
        return ResponseEntity.ok().body(tarefas);
    }
}
