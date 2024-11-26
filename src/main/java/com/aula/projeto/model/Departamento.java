package com.aula.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
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
//@Entity(name = "TBL_2120")
@Entity
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="CD_DEPTO")
    private Integer idDepto;
    
    @NotNull(message = "Nome do departamento não pode ser nulo!")
    @NotBlank(message = "Nome do departamento não pode ser branco!")
    @Length(min = 5, max = 255, message = "Nome do departamento deve ter entre 5 e 255 caracteres!")
    private String nmDepto;

    @Email
    private String email;

    @OneToMany(mappedBy="departamentoFunc")
    private List<Funcionario> funcionarios = new ArrayList<>();
}
