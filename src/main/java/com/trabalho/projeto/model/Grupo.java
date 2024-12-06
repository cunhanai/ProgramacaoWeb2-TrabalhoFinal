package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;

    @NotBlank(message = "Nome do grupo não pode ser nulo!")
    @NotEmpty(message = "Nome do grupo não pode ser branco!")
    @Length(min = 3, max = 255, message = "Nome do grupo deve ter entre 3 e 255 caracteres!")
    private String nome;

    @OneToMany(mappedBy = "grupoUsuario")
    @JsonIgnore
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "grupos")
    @JsonIgnore
    private List<Categoria> categorias;

    @ManyToOne
    @JoinColumn(name = "idSetor")
    private Setor setorGrupos;

    public Grupo(String nomeGrupo) {
        setIdGrupo(null);
        setNome(nomeGrupo);
    }
}
