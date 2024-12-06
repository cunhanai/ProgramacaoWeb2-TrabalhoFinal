package com.trabalho.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.dto.UsuarioDto;
import com.trabalho.projeto.dto.UsuarioEditadoDto;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.service.UsuarioService;




/**
 * Controlador para usuarios
 */
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * Adicionar usuario
     * @param usuarioDto Editor para adicionar um usuario:
     */
    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioService.adicionarUsuario(usuarioDto);
        return ResponseEntity.ok().body(usuario);
    }

    /**
     * Visualizar todos os usuarios cadastrados
     */ 
    @GetMapping
    public ResponseEntity<List<Usuario>> vizualizarUsuarios() {

        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
    
    /**
     * Buscar usuario
     * @param id Id do usuario que deseja encontrar:
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> verUsuario(@RequestParam int id) {
        Usuario usuario = usuarioService.buscarUsuario(id);
        return ResponseEntity.ok().body(usuario);
    }

    /**
     * Editar usuario
     * @param usuarioEditadoDto Editor para modificar um usuario:
     */
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody UsuarioEditadoDto usuarioEditadoDto) {
        Usuario usuario = usuarioService.editarUsuario(usuarioEditadoDto);

        return ResponseEntity.ok().body(usuario);
    }
    
    /**
     * Deletar usuario
     * @param id Id do usuario a ser deletado:
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MensagemDTO> deletarUsuario(@PathVariable int id) {
        try {
            usuarioService.deletarUsuario(id);
        }
        catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
}
