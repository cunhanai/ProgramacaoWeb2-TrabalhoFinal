package com.trabalho.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.projeto.model.Usuario;

public interface  UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findOneByEmail(String email);
    Usuario findOneByIsLogadoTrue();
    List<Usuario> findUsuarioByGrupoUsuario_Id(int idGrupo);
}
