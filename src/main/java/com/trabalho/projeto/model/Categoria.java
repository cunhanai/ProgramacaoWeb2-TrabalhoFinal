package com.trabalho.projeto.model;

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
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    private String tituloCategoria;

    @ManyToMany
    @JoinTable(
        name = "tarefas_categoria",
        joinColumns = @JoinColumn(name="idCategoria"),
        inverseJoinColumns = @JoinColumn(name="idTarefas"),
        uniqueConstraints = @UniqueConstraint(
            name="tarefas_categoria_unique",
            columnNames = {"idCategoria","idTarefas"}
        )
    )
    @JsonIgnore
    private List<Tarefas> tarefas;

    @ManyToMany
    @JoinTable(
        name = "grupo_categoria",
        joinColumns = @JoinColumn(name="idCategoria"),
        inverseJoinColumns = @JoinColumn(name="idGrupo"),
        uniqueConstraints = @UniqueConstraint(
            name="grupo_categoria_unique",
            columnNames = {"idCategoria","idGrupo"}
        )
    )
    @JsonIgnore
    private List<Grupo> grupos;
}
