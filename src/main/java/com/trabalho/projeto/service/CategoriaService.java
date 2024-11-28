package com.trabalho.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.CategoriaDto;
import com.trabalho.projeto.dto.GrupoCategoriaDto;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoService grupoService;

    public Categoria adicionarCategoria(CategoriaDto categoriaDto) {
        usuarioService.verificarUsuarioLogado();
        Categoria categoria = converterDtoEmEntidade(categoriaDto);
        return categoriaRepository.save(categoria);
    }

    private Categoria converterDtoEmEntidade(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto);
        return categoria;
    }

    public Categoria buscarCategoria(Integer idCategoria) {
        usuarioService.verificarUsuarioLogado();

        return categoriaRepository
                .findById(idCategoria)
                .orElseThrow(
                        () -> new com.trabalho.projeto.exception.NoSuchElementException(
                                "Categoria " + idCategoria + " não encontrada!"));
    }

    public void vincularGrupo(GrupoCategoriaDto grupoCategoriaDto) {
        usuarioService.verificarUsuarioLogado();
        Categoria categoria = buscarCategoria(grupoCategoriaDto.getIdCategoria());
        Grupo grupo = grupoService.buscarGrupoPorId(grupoCategoriaDto.getIdGrupo());

        categoria.adicionarGrupo(grupo);
        categoriaRepository.save(categoria);

    }
}
