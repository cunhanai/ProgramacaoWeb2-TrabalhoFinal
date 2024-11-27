package com.trabalho.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {
    
}
