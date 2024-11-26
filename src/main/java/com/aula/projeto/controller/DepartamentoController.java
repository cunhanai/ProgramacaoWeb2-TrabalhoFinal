package com.aula.projeto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aula.projeto.model.Departamento;
import com.aula.projeto.model.MensagemDTO;
import com.aula.projeto.repository.DepartamentoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository deptoRepository;

    @GetMapping
    public ResponseEntity<List<Departamento>> findAll() {
        List<Departamento> vDepartamentos = deptoRepository.findAll();
        return ResponseEntity.ok().body(vDepartamentos);
    }
    
    @GetMapping("/{pIdDepto}")
    public ResponseEntity<Departamento> findById(@PathVariable Integer pIdDepto) {
        Departamento vDepartamento = deptoRepository
            .findById(pIdDepto)
            .orElseThrow(
                () -> new com.aula.projeto.exception.NoSuchElementException("Departamento "+pIdDepto+" não encontrado!")
            );
        return ResponseEntity.ok().body(vDepartamento);
    }
    
    @PostMapping
    public ResponseEntity<Departamento> insDepartamento(@RequestBody Departamento pDepartamento) {
        pDepartamento.setIdDepto(null);
        deptoRepository.save(pDepartamento);
        URI vURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idDepto}").buildAndExpand(pDepartamento.getIdDepto()).toUri();
        return ResponseEntity.created(vURI).body(pDepartamento);
    }
    
    @PutMapping("/{pIdDepto}")
    public ResponseEntity<Departamento> updDepto(@PathVariable Integer pIdDepto, @RequestBody Departamento pDepartamento) {
        Departamento vDeptoAtual = deptoRepository
            .findById(pIdDepto)
            .orElseThrow(
                () -> new com.aula.projeto.exception.NoSuchElementException("Departamento "+pIdDepto+" não encontrado!")
            );
        if (pDepartamento.getNmDepto() != null) {
            vDeptoAtual.setNmDepto(pDepartamento.getNmDepto());
        }
        if (pDepartamento.getEmail() != null) {
            vDeptoAtual.setEmail(pDepartamento.getEmail());
        }
        deptoRepository.save(vDeptoAtual);
        return ResponseEntity.ok().body(vDeptoAtual);
    }

    @DeleteMapping("/{pIdDepto}")
    public ResponseEntity<MensagemDTO> delDepartamento(@PathVariable Integer pIdDepto) {
        Departamento vDepto = deptoRepository
            .findById(pIdDepto)
            .orElseThrow(
                () -> new com.aula.projeto.exception.NoSuchElementException("Departamento "+pIdDepto+" não encontrado!")
            );

        try {
            deptoRepository.deleteById(pIdDepto);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new com.aula.projeto.exception.DataIntegrityViolationException("Departamento "+vDepto.getNmDepto()+" possui funcionários alocados. Não pode ser excluído!");
        }
        
        return ResponseEntity.ok().body(new MensagemDTO("Ok","Departamento "+pIdDepto+" deletado com sucesso!"));
    }
}
