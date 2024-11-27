package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trabalho.projeto.dto.CategoriaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotBlank
    @NotEmpty
    @Length(min = 3, max = 255, message = "Titulo da categoria deve ter entre 3 e 255 caracteres!")
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

    public Categoria(CategoriaDto categoriaDto) {
        setIdCategoria(null);
        setTituloCategoria(categoriaDto.getTituloCategoria());
    }
}
