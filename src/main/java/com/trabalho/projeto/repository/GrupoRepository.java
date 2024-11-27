package com.trabalho.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    
}
