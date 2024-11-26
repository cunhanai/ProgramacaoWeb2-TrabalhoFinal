package com.aula.projeto.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class Teste {

    @GetMapping("/")
    public String olaMundo() {
        return "Olá, mundo!";
    }

    @GetMapping("/teste")
    public String retornaTeste() {
        return "Utilizando endpoint /teste";
    }

    @PostMapping("/")
    public String olamundoPOST() {
        return "Olá, mundo via POST!";
    }
    
    @PutMapping("/")
    public String olaMundoPUT() {
        return "Olá, mundo via PUT!";
    }

    @DeleteMapping("/")
    public String olaMundoDELETE() {
        return "Olá, mundo via DELETE!";
    }

    @GetMapping("/teste/{parametro}")
    public String getTesteParametro(@PathVariable Integer parametro) {
        return "Você passou "+parametro+" como parâmetro";
    }

    @GetMapping(value = "/geraJSON", produces = "application/json")
    public String geraJSON() {
        return "{ \"status\" : \"JSON gerado!\" }";
    }
}
