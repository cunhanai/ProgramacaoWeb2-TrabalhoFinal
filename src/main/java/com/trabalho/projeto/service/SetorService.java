package com.trabalho.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.dto.SetorGrupoDto;
import com.trabalho.projeto.exception.DataIntegrityViolationException;
import com.trabalho.projeto.model.Grupo;
import com.trabalho.projeto.model.Setor;
import com.trabalho.projeto.repository.GrupoRepository;
import com.trabalho.projeto.repository.SetorRepository;

@Service
public class SetorService {
    
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    private SetorRepository setorRepository;

    public Setor adicionarSetor(String nomeSetor) {
        if (buscarSetorPorNome(nomeSetor) != null)
            throw new DataIntegrityViolationException("Nome do setor \"" + nomeSetor + "\" já existe!");

        Setor setor = new Setor(nomeSetor);
        return setorRepository.save(setor);
    }

    public void deletarSetor(int id) {
        Setor setor = buscarSetorPorId(id);

        List<Grupo> grupos = setor.getGrupos();
        
        for (Grupo grupo : grupos)
            grupo.setSetorGrupos(null);

        grupoRepository.saveAll(grupos);

        setorRepository.delete(setor);
    }

    public List<Setor> buscarTodosSetores() {
        return setorRepository.findAll();
    }

    public Setor buscarSetorPorId(int id) {
        return setorRepository
            .findById(id)
            .orElseThrow(
                () -> new com.trabalho.projeto.exception.NoSuchElementException("Setor "+ id +" não encontrado!")
        );
    }

        public Setor buscarSetorPorNome(String nomeSetor) {
        usuarioService.verificarUsuarioLogado();
        return setorRepository.findOneByNome(nomeSetor);
    }

    public List<Setor> listarSetores() {
        usuarioService.verificarUsuarioLogado();
        return setorRepository.findAll();
    }

    public Setor editarSetor(Setor setorEditado) {
        Setor setor = buscarSetorPorId(setorEditado.getIdSetor());


        if (!setorEditado.getNome().isBlank()) {
            setor.setNome(setorEditado.getNome());
            setorRepository.save(setor);
        }

        return setor;
    }

    public void vincularGrupo(SetorGrupoDto setorGrupoDto) {
        usuarioService.verificarUsuarioLogado();
        Setor setor = buscarSetorPorId(setorGrupoDto.getIdSetor());
        Grupo grupo = grupoService.buscarGrupoPorId(setorGrupoDto.getIdGrupo());

        grupo.setSetorGrupos(setor);
        grupoRepository.save(grupo);
    }

    public List<Grupo> listarGrupos(int idGrupo) {
        usuarioService.verificarUsuarioLogado();
        return grupoService.buscarGrupoPorSetor(idGrupo);
    }
}
