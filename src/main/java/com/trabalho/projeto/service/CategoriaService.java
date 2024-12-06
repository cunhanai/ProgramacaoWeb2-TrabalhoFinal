package com.trabalho.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.GrupoCategoriaDto;
import com.trabalho.projeto.model.Categoria;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Tarefas;
import com.trabalho.projeto.model.Usuario;
import com.trabalho.projeto.repository.CategoriaRepository;
import com.trabalho.projeto.repository.TarefasRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private TarefasRepository tarefasRepository;

    public Categoria adicionarCategoria(String categoriaNova) {
        usuarioService.verificarUsuarioLogado();
        Usuario usuario = usuarioService.buscarUsuarioLogado();
        
        Categoria categoria = new Categoria(categoriaNova);
        categoria.adicionarGrupo(usuario.getGrupoUsuario());

        return categoriaRepository.save(categoria);
    }

    public Categoria buscarCategoriaPorId(Integer idCategoria) {
        usuarioService.verificarUsuarioLogado();

        return categoriaRepository
                .findById(idCategoria)
                .orElseThrow(
                        () -> new com.trabalho.projeto.exception.NoSuchElementException(
                                "Categoria " + idCategoria + " não encontrada!"));
    }

    public void vincularGrupo(GrupoCategoriaDto grupoCategoriaDto) {
        usuarioService.verificarUsuarioLogado();
        Categoria categoria = buscarCategoriaPorId(grupoCategoriaDto.getIdCategoria());
        Grupo grupo = grupoService.buscarGrupoPorId(grupoCategoriaDto.getIdGrupo());

        categoria.adicionarGrupo(grupo);
        categoriaRepository.save(categoria);
    }

    public List<Categoria> vizualizarCategorias() {
        usuarioService.verificarUsuarioLogado();
        return categoriaRepository.findAll();
    }

    public Categoria editarCategoria(Categoria categoriaEditada) {
        Categoria categoria = buscarCategoriaPorId(categoriaEditada.getIdCategoria());

        if (!categoriaEditada.getTituloCategoria().isBlank())
        {
            categoria.setTituloCategoria(categoriaEditada.getTituloCategoria());
            categoriaRepository.save(categoria);
        }

        return categoria;
    }

    public void deletarCategoria(int id) {
        Categoria categoria = buscarCategoriaPorId(id);
        List<Tarefas> tarefas = categoria.getTarefas();

        for (Tarefas tarefa : tarefas)
            tarefa.removerCategoria(categoria);
        
        tarefasRepository.saveAll(tarefas);

        categoriaRepository.delete(categoria);
    }

}
