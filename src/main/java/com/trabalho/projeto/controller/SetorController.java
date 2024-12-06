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

import com.trabalho.projeto.dto.SetorGrupoDto;
import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Setor;
import com.trabalho.projeto.service.SetorService;

@RestController
@RequestMapping(value = "/setor")
public class SetorController {

    @Autowired
    SetorService setorService;

    @PostMapping()
    public ResponseEntity<Setor> adicionarSetor(@RequestParam String nomeSetor) {
        Setor setor = setorService.adicionarSetor(nomeSetor);

        return ResponseEntity.ok().body(setor);
    }

    @PutMapping()
    public ResponseEntity<Setor> editarSetor(@RequestBody Setor setorEditado) {
        Setor setor = setorService.editarSetor(setorEditado);

        return ResponseEntity.ok().body(setor);
    }

    @GetMapping
    public ResponseEntity<List<Setor>> vizualizarSetores() {
        List<Setor> setores = setorService.listarSetores();
        return ResponseEntity.ok().body(setores);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MensagemDTO> deletarSetores(@PathVariable int id) {
        try {
            setorService.deletarSetor(id);
        } catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }

    @PutMapping("/vincular-grupo")
    public ResponseEntity<MensagemDTO> vincularGrupo(@RequestBody SetorGrupoDto setorGrupoDto) {
        setorService.vincularGrupo(setorGrupoDto);

        return ResponseEntity.ok().body(new MensagemDTO("OK", "Grupo \"" + setorGrupoDto.getIdGrupo()
                + "\" vinculado ao setor \"" + setorGrupoDto.getIdSetor() + "\"."));
    }

    @GetMapping("/grupos/{idSetor}")
    public ResponseEntity<List<Grupo>> vizualizarSetores(@PathVariable int idSetor) {
        List<Grupo> grupos = setorService.listarGrupos(idSetor);
        return ResponseEntity.ok().body(grupos);
    }
}
