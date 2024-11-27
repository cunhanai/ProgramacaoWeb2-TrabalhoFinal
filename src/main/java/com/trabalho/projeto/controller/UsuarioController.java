package com.trabalho.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.UsuarioDto;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.service.UsuarioService;


@RequestMapping(value = "/usuario")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioService.adicionarUsuario(usuarioDto);
        return ResponseEntity.ok().body(usuario);
    }
    
}
