package com.aula.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aula.projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {

    @Query(value = "SELECT id_funcionario, email, nm_funcionario, id_depto FROM funcionario WHERE id_depto = :pIdDepartamento ORDER BY nm_funcionario", nativeQuery = true)
    List<Funcionario> searchByDepto(@Param("pIdDepartamento") Integer pIdDepartamento);

    @Query(value="SELECT id_funcionario, email, nm_funcionario, id_depto FROM funcionario WHERE upper(nm_funcionario) LIKE '%'||upper(:pNome)||'%' ORDER BY nm_funcionario", nativeQuery = true)
    List<Funcionario> searchByNome(@Param("pNome") String pNome);
}
