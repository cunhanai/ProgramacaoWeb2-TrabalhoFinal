package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trabalho.projeto.dto.TarefasDto;

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

    @NotBlank
    @NotEmpty
    @Length(min = 3, max = 255, message = "[] da tarefa deve ter entre 3 e 255 caracteres!")
    private String tituloTarefa;

    @Length(min = 3, max = 9999, message = "[] da tarefa deve ter entre 3 e 9999 caracteres!")
    private String descricaoTarefa;

    private Integer prioridadeTarefa;

    private boolean concluidaTarefa;
    
    @ManyToMany(mappedBy = "tarefas")
    @JsonIgnore
    private List<Categoria> categoria;

    @NotBlank
    @NotEmpty
    @OneToMany(mappedBy = "tarefas")
    @JsonIgnore
    private List<Usuario> usuarios;

    public Tarefas(TarefasDto tarefasDto) {
        setIdTarefa(null);
        setTituloTarefa(tarefasDto.getTituloTarefa());
        setDescricaoTarefa(tarefasDto.getDescricaoTarefa());
        setPrioridadeTarefa(tarefasDto.getPrioridadeTarefa());
        setConcluidaTarefa(tarefasDto.getConcluidaTarefa());
    }
}
