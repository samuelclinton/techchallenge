package br.com.postech.techchallenge.dtos.responses;

import lombok.Data;

@Data
public final class EletrodomesticoDtoResponse implements PoliticaDtoResponse<String> {

  private String id;
  private String nome;
  private String modelo;
  private String potencia;
  private String marca;
}

