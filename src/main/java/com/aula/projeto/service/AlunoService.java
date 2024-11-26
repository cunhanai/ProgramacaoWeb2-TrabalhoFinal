package com.aula.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.projeto.model.Aluno;
import com.aula.projeto.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno insAluno(Aluno pAluno) {
        pAluno.setIdAluno(null);
        return alunoRepository.save(pAluno);
    }
}
