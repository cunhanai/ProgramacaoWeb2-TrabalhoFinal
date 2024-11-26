package com.aula.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.projeto.model.Aluno;
import com.aula.projeto.model.Disciplina;
import com.aula.projeto.repository.AlunoRepository;
import com.aula.projeto.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    
    public Disciplina insereDisciplina(Disciplina pDisciplina) {
        pDisciplina.setIdDisciplina(null);
        return disciplinaRepository.save(pDisciplina);
    }

    public Disciplina insAlunoDisciplina (Integer pIdDisciplina, Integer pIdAluno) {
        Aluno vAluno = alunoRepository
            .findById(pIdAluno)
            .orElseThrow();
            
        Disciplina vDisciplina = disciplinaRepository
            .findById(pIdDisciplina)
            .orElseThrow();

        List<Aluno> vAlunos = vDisciplina.getAlunos();
        vAlunos.add(vAluno);
        vDisciplina.setAlunos(vAlunos);

        try {
            vDisciplina = disciplinaRepository.save(vDisciplina);    
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new com.aula.projeto.exception.DataIntegrityViolationException("Aluno "+vAluno.getNmAluno()+" já está matriculado na disciplina "+vDisciplina.getNmDisciplina()+"!");
        }

        return vDisciplina;
    }
}
