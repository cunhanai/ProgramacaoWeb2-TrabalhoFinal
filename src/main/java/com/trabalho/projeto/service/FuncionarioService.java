package com.trabalho.projeto.service;
/*
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.projeto.model.Funcionario;
import com.trabalho.projeto.model.Setor;
//import com.trabalho.projeto.repository.DepartamentoRepository;
import com.trabalho.projeto.repository.FuncionarioRepository;

@Service*/
public class FuncionarioService {
/*
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Integer pIdFuncionario) {
        Funcionario vFuncionario = funcionarioRepository
            .findById(pIdFuncionario)
            .orElseThrow();

        return vFuncionario;
    }

    public Funcionario insFuncionario(Funcionario pFuncionario, Integer pIdDepartamento) {
        Setor vDepartamento = departamentoRepository
            .findById(pIdDepartamento)
            .orElseThrow();

        pFuncionario.setIdFuncionario(null);
        pFuncionario.setDepartamentoFunc(vDepartamento);

        return funcionarioRepository.save(pFuncionario);
    }

    public Funcionario updFuncionario(Funcionario pFuncionario, Integer pIdDepartamento) {
        Funcionario vFuncAtual = funcionarioRepository
            .findById(pFuncionario.getIdFuncionario())
            .orElseThrow();

        Setor vDepartamento = departamentoRepository
            .findById(pIdDepartamento)
            .orElseThrow();

        if (pFuncionario.getNmFuncionario() != null) {
            vFuncAtual.setNmFuncionario(pFuncionario.getNmFuncionario());
        }
        if (pFuncionario.getEmail() != null) {
            vFuncAtual.setEmail(pFuncionario.getEmail());
        }
        vFuncAtual.setDepartamentoFunc(vDepartamento);

        return funcionarioRepository.save(vFuncAtual);
    }

    public void delFuncionario(Integer pIdFuncionario) {
        funcionarioRepository
            .findById(pIdFuncionario)
            .orElseThrow();

        funcionarioRepository.deleteById(pIdFuncionario);
    }

    public List<Funcionario> searchByDepto(Integer pIdDepartamento) {

        Setor vDepto = departamentoRepository
            .findById(pIdDepartamento)
            .orElseThrow(
                () -> new com.trabalho.projeto.exception.NoSuchElementException("Departamento "+pIdDepartamento+" não encontrado!")
            );

        List<Funcionario> funcionarios = funcionarioRepository.searchByDepto(pIdDepartamento);

        if (funcionarios.isEmpty()) {
            throw new com.trabalho.projeto.exception.NoSuchElementException("Departamento "+pIdDepartamento+" - "+vDepto.getNmDepto()+" não possui funcionários!");
        }

        return funcionarios;
    }

    public List<Funcionario> searchByNome(String pNome) {
        return funcionarioRepository.searchByNome(pNome);
    }
        */
}
