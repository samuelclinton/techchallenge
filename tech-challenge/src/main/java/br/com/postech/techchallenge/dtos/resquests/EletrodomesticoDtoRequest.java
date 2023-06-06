package br.com.postech.techchallenge.dtos.resquests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class EletrodomesticoDtoRequest implements PoliticaDtoRequest {

  @NotBlank
  private String nome;

  @NotBlank
  private String modelo;

  @NotBlank
  private String potencia;

  @NotBlank
  private String marca;
}

