package com.trabalho.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
