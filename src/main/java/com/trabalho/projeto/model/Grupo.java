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
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotEmpty
    @Length(min = 3, max = 255, message = "Nome do grupo deve ter entre 3 e 255 caracteres!")
    private String nome;

    @OneToMany(mappedBy = "grupoUsuario")
    @JsonIgnore
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "grupos")
    @JsonIgnore
    private List<Categoria> categorias;

    public Grupo(String nomeGrupo) {
        setId(null);
        setNome(nomeGrupo);
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuarios == null)
            usuarios = new ArrayList<Usuario>();

        usuarios.add(usuario);
    }
}
