package br.com.postech.techchallenge.controllers.filtros;

import br.com.postech.techchallenge.entities.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFiltro implements PoliticaFiltro<Long> {

  private Long id;

  private String nome;

  private SexoEnum sexo;

  private String cpf;

  private String parentesco;
}

