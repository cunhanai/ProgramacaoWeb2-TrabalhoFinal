package com.trabalho.projeto.dto;

import lombok.Getter;

@Getter
public class TarefasDto {
    private String tituloTarefa;
    private String descricaoTarefa;
    private int prioridadeTarefa;
    private boolean concluidaTarefa;
    private int idCategoria;
}
