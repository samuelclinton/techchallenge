package br.com.postech.techchallenge.dtos.responses;

import br.com.postech.techchallenge.entities.enums.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class PessoaDtoResponse implements PoliticaDtoResponse {

  private Long id;

  private String nome;

  private String dataNascimento; // dd/MM/yyyy

  private SexoEnum sexo;

  private String cpf;

  private String parentesco;

  private Instant dataCadastro; // UTC
}

