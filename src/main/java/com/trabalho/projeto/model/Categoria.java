package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotBlank(message = "Título da categoria não pode ser nulo!")
    @NotEmpty(message = "Título da categoria não pode ser branco!")
    @Length(min = 3, max = 255, message = "Título da categoria deve ter entre 3 e 255 caracteres!")
    private String tituloCategoria;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Tarefas> tarefas;

    @ManyToMany
    @JoinTable(name = "grupo_categoria", joinColumns = @JoinColumn(name = "idCategoria"), inverseJoinColumns = @JoinColumn(name = "idGrupo"), uniqueConstraints = @UniqueConstraint(name = "grupo_categoria_unique", columnNames = {
            "idCategoria", "idGrupo" }))
    private List<Grupo> grupos;

    public Categoria(String categoria) {
        setIdCategoria(null);
        setTituloCategoria(categoria);
    }

    public void adicionarGrupo(Grupo grupo) {
        if (grupos == null)
            grupos = new ArrayList<>();

        grupos.add(grupo);
    }

    public void removerTarefas() {
        tarefas = null;
    }
}
