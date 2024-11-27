package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.GrupoDto;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.repository.GrupoRepository;

@Service
public class GrupoService {
    
    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo adicionarGrupo(GrupoDto grupoDto) {
        Grupo grupo = converterDtoEmEntidade(grupoDto);
        return grupoRepository.save(grupo);
    }

    public Grupo converterDtoEmEntidade(GrupoDto grupoDto) {
        Grupo grupo = new Grupo(grupoDto);
        return grupo;
    }
}
