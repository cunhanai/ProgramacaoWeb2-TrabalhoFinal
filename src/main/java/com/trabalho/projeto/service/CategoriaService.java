package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.CategoriaDto;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria adicionarCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = converterDtoEmEntidade(categoriaDto);
        return categoriaRepository.save(categoria);
    }

    private Categoria converterDtoEmEntidade(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto);
        return categoria;
    }
}
