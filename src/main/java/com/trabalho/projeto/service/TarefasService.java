package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.TarefasDto;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.repository.TarefasRepository;

@Service
public class TarefasService {
    
    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefas adicionarTarefas(TarefasDto tarefasDto) {
        usuarioService.verificarUsuarioLogado();
        Tarefas tarefas =  new Tarefas(tarefasDto);

        if (tarefasDto.getIdCategoria() > 0) {
            Categoria categoria = categoriaService.buscarCategoria(tarefasDto.getIdCategoria());
            tarefas.adicionarCategoria(categoria);
        }

        return tarefasRepository.save(tarefas);
    }

}
