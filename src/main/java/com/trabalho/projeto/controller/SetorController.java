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

/**
 * Controlador para setores
 */
@RestController
@RequestMapping(value = "/setor")
public class SetorController {

    @Autowired
    SetorService setorService;

    /**
     * Adicionar setor
     * @param nomeSetor Insira o nome do novo setor:
     */
    @PostMapping()
    public ResponseEntity<Setor> adicionarSetor(@RequestParam String nomeSetor) {
        Setor setor = setorService.adicionarSetor(nomeSetor);

        return ResponseEntity.ok().body(setor);
    }

    /**
     * Editar setor
     * @param setorEditado Editor para modificar um setor:
     */
    @PutMapping()
    public ResponseEntity<Setor> editarSetor(@RequestBody Setor setorEditado) {
        Setor setor = setorService.editarSetor(setorEditado);

        return ResponseEntity.ok().body(setor);
    }

    /**
     * Visualizar todos os setores
     */
    @GetMapping
    public ResponseEntity<List<Setor>> vizualizarSetores() {
        List<Setor> setores = setorService.listarSetores();
        return ResponseEntity.ok().body(setores);
    }

    /**
     * Deletar setor
     * @param id Id do setor a ser deletado:
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MensagemDTO> deletarSetores(@PathVariable int id) {
        try {
            setorService.deletarSetor(id);
        } catch (com.trabalho.projeto.exception.NoSuchElementException e) {
            return ResponseEntity.ok().body(new MensagemDTO("ERRO", e.getMessage()));
        }

        return ResponseEntity.ok().body(new MensagemDTO("OK", "OK"));
    }

    /**
     * Vincular um grupo ao setor
     * @param setorGrupoDto Editor para vincular um grupo a um setor:
     */
    @PutMapping("/vincular-grupo")
    public ResponseEntity<MensagemDTO> vincularGrupo(@RequestBody SetorGrupoDto setorGrupoDto) {
        setorService.vincularGrupo(setorGrupoDto);

        return ResponseEntity.ok().body(new MensagemDTO("OK", "Grupo \"" + setorGrupoDto.getIdGrupo()
                + "\" vinculado ao setor \"" + setorGrupoDto.getIdSetor() + "\"."));
    }

    /**
     * Vizualiza todos os grupos vinculado ao setor
     * @param id Id do setor requerido
     */
    @GetMapping("/grupos/{id}")
    public ResponseEntity<List<Grupo>> vizualizarSetores(@PathVariable int id) {
        List<Grupo> grupos = setorService.listarGrupos(id);
        return ResponseEntity.ok().body(grupos);
    }
}
