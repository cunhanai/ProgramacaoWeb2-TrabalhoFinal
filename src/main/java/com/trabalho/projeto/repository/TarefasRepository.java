package com.trabalho.projeto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {
    
    Optional<Tarefas> findOneByIdTarefaAndUsuarios_IdUsuario(int id, int idUsuario);

    List<Tarefas> findTarefasByUsuarios_IdUsuario(int idUsuario);
}
