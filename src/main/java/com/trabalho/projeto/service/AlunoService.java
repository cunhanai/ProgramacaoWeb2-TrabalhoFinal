package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.model.Aluno;
import com.trabalho.projeto.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno insAluno(Aluno pAluno) {
        pAluno.setIdAluno(null);
        return alunoRepository.save(pAluno);
    }
}
