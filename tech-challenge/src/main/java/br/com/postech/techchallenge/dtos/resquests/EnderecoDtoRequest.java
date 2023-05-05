package br.com.postech.techchallenge.dtos.resquests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
public final class EnderecoDtoRequest implements PoliticaDtoRequest {

  @NotBlank
  private String rua;

  @NotBlank
  @Length(max = 16)
  private String numero;

  @NotBlank
  private String bairro;

  @NotBlank
  private String cidade;

  @NotBlank
  @Length(max = 2)
  private String estado;
}

