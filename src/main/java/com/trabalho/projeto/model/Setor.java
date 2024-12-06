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
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Setor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSetor;
    
    @NotNull(message = "Nome do setor não pode ser nulo!")
    @NotBlank(message = "Nome do setor não pode ser branco!")
    @Length(min = 5, max = 255, message = "Nome do setor deve ter entre 5 e 255 caracteres!")
    private String nome;

    @OneToMany(mappedBy="setorGrupos")
    @JsonIgnore
    private List<Grupo> grupos;
    
    public Setor(String nomeSetor) {
        setIdSetor(null);
        setNome(nomeSetor);
    }

    public void adicionarGrupo(Grupo grupo) {
        if (grupos == null)
            grupos = new ArrayList<Grupo>();

        grupos.add(grupo);
    }
}
