package com.aula.projeto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aula.projeto.model.Funcionario;
import com.aula.projeto.model.MensagemDTO;
import com.aula.projeto.service.FuncionarioService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<Funcionario>> findAll() {
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }
    
    @GetMapping(value="/{pIdFuncionario}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer pIdFuncionario) {
        return ResponseEntity.ok().body(funcionarioService.findById(pIdFuncionario));
    }

    @GetMapping("/departamento/{pIdDepartamento}")
    public ResponseEntity<List<Funcionario>> searchByDepto(@PathVariable Integer pIdDepartamento) {
        return ResponseEntity.ok().body(funcionarioService.searchByDepto(pIdDepartamento));
    }
    
    @GetMapping("/nome/{pNome}")
    public ResponseEntity<List<Funcionario>> searchByNome(@PathVariable String pNome) {
        return ResponseEntity.ok().body(funcionarioService.searchByNome(pNome));
    }
    
    @PostMapping(value="/{pIdDepartamento}")
    public ResponseEntity<Funcionario> insFuncionario(@RequestBody Funcionario pFuncionario, @PathVariable Integer pIdDepartamento) {
        Funcionario vNovoFuncionario = funcionarioService.insFuncionario(pFuncionario, pIdDepartamento);
        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{pIdFuncionario}").buildAndExpand(vNovoFuncionario.getIdFuncionario()).toUri();

        return ResponseEntity.created(vUri).body(vNovoFuncionario);
    }
    
    @PutMapping("/{pIdDepartamento}")
    public ResponseEntity<Funcionario> updFuncionario(@PathVariable Integer pIdDepartamento, @RequestBody Funcionario pFuncionario) {
        Funcionario vFuncAlterado = funcionarioService.updFuncionario(pFuncionario, pIdDepartamento);
        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionario/{pIdFuncionario}").buildAndExpand(vFuncAlterado.getIdFuncionario()).toUri();

        return ResponseEntity.created(vUri).body(vFuncAlterado);
    }

    @DeleteMapping(value="/{pIdFuncionario}")
    public ResponseEntity<MensagemDTO> delFuncionario(@PathVariable Integer pIdFuncionario) {
        funcionarioService.delFuncionario(pIdFuncionario);
        return ResponseEntity.ok().body(new MensagemDTO("Ok", "Funcionário "+pIdFuncionario+" deletado com sucesso!"));
    }
}
