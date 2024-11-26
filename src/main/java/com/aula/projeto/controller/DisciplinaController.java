package com.aula.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.projeto.model.Disciplina;
import com.aula.projeto.model.MensagemDTO;
import com.aula.projeto.service.DisciplinaService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping(value = "/disciplina")
@RestController
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> insDisciplina(@RequestBody Disciplina pDisciplina) {
        return ResponseEntity.ok().body(disciplinaService.insereDisciplina(pDisciplina));
    }

    @PostMapping("/{pIdDisciplina}/aluno/{pIdAluno}")
    public ResponseEntity<MensagemDTO> insAlunoDisciplina(@PathVariable Integer pIdDisciplina, @PathVariable Integer pIdAluno) {
        disciplinaService.insAlunoDisciplina(pIdDisciplina, pIdAluno);
        
        return ResponseEntity.ok().body(new MensagemDTO("Ok", "Aluno "+pIdAluno+" inserido na disciplina "+pIdDisciplina));
    }
}
