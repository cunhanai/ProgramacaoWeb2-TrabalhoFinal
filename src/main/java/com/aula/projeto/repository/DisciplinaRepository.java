package com.aula.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.projeto.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
