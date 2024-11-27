package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.TarefasDto;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.repository.TarefasRepository;

@Service
public class TarefasService {
    
    @Autowired
    private TarefasRepository tarefasRepository;

    public Tarefas adicionarTarefas(TarefasDto tarefasDto) {
        Tarefas tarefas = converterDtoEmEntidade(tarefasDto);
        return tarefasRepository.save(tarefas);
    }

    private Tarefas converterDtoEmEntidade(TarefasDto tarefasDto) {
        Tarefas tarefas = new Tarefas(tarefasDto);
        return tarefas;
    }
}
