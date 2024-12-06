package com.trabalho.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Setor;

public interface SetorRepository extends JpaRepository<Setor,Integer> {

    Setor findOneByNome(String nome);
}
