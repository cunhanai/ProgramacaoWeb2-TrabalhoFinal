package com.trabalho.projeto.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trabalho.projeto.dto.GrupoUsuarioDto;
import com.trabalho.projeto.dto.MensagemDTO;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Setor;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.SetorRepository;




@RestController
@RequestMapping(value = "/setor")
public class SetorController {

    @Autowired
    private SetorRepository setorRepository;

    @PostMapping()
    public ResponseEntity<Setor> adicionarSetor(@RequestParam String nomeSetor) {
        Setor setor = setor.adicionarGrupo(nomeSetor);
        
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
        }
        catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }
    
    @PutMapping("/vincular-grupo")
    public ResponseEntity<MensagemDTO> vincularGrupo(@RequestBody SetorGrupoDto setorGrupoDto) {
        setorService.vincularGrupo(setorGrupoDto);
        
        return ResponseEntity.ok().body(new MensagemDTO("OK", "Grupo \"" + setorGrupoDto.getIdGrupo() + "\" vinculado ao setor \"" + setorGrupoDto.getIdSetor() + "\"."));
    }

    @GetMapping("/usuarios/{idSetor}") 
    public ResponseEntity<List<Usuario>> vizualizarGrupos(@PathVariable int idGrupo) {
        List<Usuario> usuarios = grupoService.listarUsuarios(idGrupo);
        return ResponseEntity.ok().body(usuarios);
    }
}
