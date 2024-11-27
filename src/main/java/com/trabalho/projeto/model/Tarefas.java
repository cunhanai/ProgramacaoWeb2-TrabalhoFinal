package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Tarefas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarefa;
    private String tituloTarefa;
    private String descricaoTarefa;
    private Integer prioridadeTareda;
    private boolean concluidaTarefa;
    
    @ManyToMany(mappedBy = "tarefas")
    @JsonIgnore
    private List<Categoria> categoria;

    @NotBlank
    @NotEmpty
    @OneToMany(mappedBy = "tarefas")
    @JsonIgnore
    private List<Usuario> usuarios;
}
