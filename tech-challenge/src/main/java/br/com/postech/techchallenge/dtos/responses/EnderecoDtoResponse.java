package br.com.postech.techchallenge.dtos.responses;

import lombok.Data;

@Data
public final class EnderecoDtoResponse implements PoliticaDtoResponse<String> {

  private String id;
  private String rua;
  private String numero;
  private String bairro;
  private String cidade;
  private String estado;
}

