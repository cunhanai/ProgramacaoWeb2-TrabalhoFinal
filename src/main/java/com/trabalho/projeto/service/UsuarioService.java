package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.UsuarioDto;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario adicionarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = converterDtoEmEntidade(usuarioDto);
        return usuarioRepository.save(usuario);
    }

    private Usuario converterDtoEmEntidade(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto);
        return usuario;
    }
}
