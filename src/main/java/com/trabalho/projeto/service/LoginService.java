package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.LoginDto;
import com.trabalho.projeto.exception.InvalidLoginException;
import com.trabalho.projeto.exception.NoSuchElementException;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String fazerLogin(LoginDto loginDto) {
        
        Usuario usuario = usuarioRepository.findOneByEmail(loginDto.getEmail());

        if (usuario == null) 
            throw new NoSuchElementException("Usuário não encontrado!");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(loginDto.getSenha(), usuario.getSenha()))
            throw new InvalidLoginException("Senha incorreta!");

        usuario.setLogado(true);
        usuarioRepository.save(usuario);

        return "Usuário Logado";
    }
}
