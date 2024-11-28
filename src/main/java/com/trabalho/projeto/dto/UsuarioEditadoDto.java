package com.trabalho.projeto.dto;

import lombok.Getter;

@Getter
public class UsuarioEditadoDto {
    private int idUsuario;
    private String nome;
    private String email;
    private String senhaAtual;
    private String senhaNova;
}
