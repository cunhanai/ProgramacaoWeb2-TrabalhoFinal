package com.aula.projeto.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aula.projeto.model.Aluno;
import com.aula.projeto.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/aluno")
@RestController
public class AlunoController {

    @Autowired
    AlunoService alunoService;
    
    @PostMapping
    public ResponseEntity<Aluno> insAluno(@RequestBody Aluno pAluno) {
        alunoService.insAluno(pAluno);
        
        return ResponseEntity.ok().body(pAluno);
    }
    
}
