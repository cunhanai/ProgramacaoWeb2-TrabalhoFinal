package com.trabalho.projeto.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trabalho.projeto.dto.UsuarioDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
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
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank(message = "Nome do usuário não pode ser nulo!")
    @NotEmpty(message = "Nome do Email do usuário não pode ser branco!")
    @Length(min = 3, max = 255, message = "Nome do usuário deve ter entre 3 e 255 caracteres!")
    private String nome;

    @NotBlank(message = "Email do usuário não pode ser nulo!")
    @NotEmpty(message = "Email do usuário não pode ser branco!")
    @Column(unique=true)
    @Email(message="E-mail inválido!")
    private String email;

    @JsonIgnore
    @Length(min = 5, max = 15, message = "Senha deve ter entre 5 e 15 caracteres!")
    private String senhaDecriptada;

    @NotBlank(message = "Senha do usuário não pode ser nulo!")
    @NotEmpty(message = "Senha do usuário não pode ser branco!")
    @Column(length=60)
    private String senha;

    private boolean isLogado;

    @ManyToOne
    @JoinColumn(name = "idGrupo")
    @JsonIgnore
    private Grupo grupoUsuario;

    @ManyToAny
    @JoinTable(
        name = "tarefa_usuario",
        joinColumns = @JoinColumn(name="idTarefa"),
        inverseJoinColumns = @JoinColumn(name="idUsuario"),
        uniqueConstraints = @UniqueConstraint(
            name="tarefa_usuario_unique",
            columnNames = {"idUsuario","idTarefa"}
        )
    )
    @JsonIgnore
    private List<Tarefas> tarefas;

    public Usuario(UsuarioDto usuarioDto) {
        setIdUsuario(null);
        setNome(usuarioDto.getNome());
        setEmail(usuarioDto.getEmail());
        setLogado(false);
        definirSenhaEncriptada(usuarioDto.getSenha());
    }

    public void definirSenhaEncriptada(String senhaDecriptada) {
        this.senha = new BCryptPasswordEncoder().encode(senhaDecriptada);
        this.senhaDecriptada = senhaDecriptada;
    }
}
