package com.aula.projeto.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Disciplina implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisciplina;
    private String nmDisciplina;

    @ManyToMany
    @JoinTable(
        name = "disciplina_aluno",
        joinColumns = @JoinColumn(name="id_disciplina"),
        inverseJoinColumns = @JoinColumn(name="id_aluno"),
        uniqueConstraints = @UniqueConstraint(
            name="disciplina_aluno_unique",
            columnNames = {"id_disciplina","id_aluno"}
        )
    )
    @JsonIgnore
    private List<Aluno> alunos;
}
