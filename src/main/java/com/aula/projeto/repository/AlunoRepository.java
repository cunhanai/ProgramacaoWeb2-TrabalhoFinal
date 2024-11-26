package com.aula.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.projeto.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

}
