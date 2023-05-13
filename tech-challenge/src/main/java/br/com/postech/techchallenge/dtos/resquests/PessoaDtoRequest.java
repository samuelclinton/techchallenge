package br.com.postech.techchallenge.dtos.resquests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cpf"})
public final class PessoaDtoRequest implements PoliticaDtoRequest {

  @NotBlank
  @Length(max = 150)
  private String nome;

  @NotBlank
  @Length(min = 10, max = 10)
  private String dataNascimento; // dd/MM/yyyy

  @NotBlank
  private String sexo;

  @NotBlank
  @CPF
  @Length(max = 14)
  private String cpf; // 986.705.000-27

  @Length(max = 50)
  private String parentesco;
}

