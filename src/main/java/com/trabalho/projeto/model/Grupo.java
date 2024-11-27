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
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;
    private String nomeGrupo;

    @ManyToMany
    @JoinTable(
        name = "grupo_usuario",
        joinColumns = @JoinColumn(name="idGrupo"),
        inverseJoinColumns = @JoinColumn(name="idUsuario"),
        uniqueConstraints = @UniqueConstraint(
            name="grupo_usuario_unique",
            columnNames = {"idGrupo","idUsuario"}
        )
    )
    @JsonIgnore
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "grupos")
    @JsonIgnore
    private List<Categoria> categorias;

}
