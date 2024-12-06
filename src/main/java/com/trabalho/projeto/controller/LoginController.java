package com.trabalho.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.projeto.dto.LoginDto;
import com.trabalho.projeto.service.LoginService;


/**
 * Controlador para login
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    
    @Autowired
    LoginService loginService;

    /**
     * Login
     * @param loginDto Insira login e senha:
     */
    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody LoginDto loginDto) {
        String token = loginService.fazerLogin(loginDto);
        return ResponseEntity.ok().body(token);
    }
    
}
