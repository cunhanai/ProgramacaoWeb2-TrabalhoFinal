package com.trabalho.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    Grupo findOneByNome(String nomeGrupo);
    List<Grupo> findGrupoBySetorGrupos_IdSetor(int idSetor);
    
}
