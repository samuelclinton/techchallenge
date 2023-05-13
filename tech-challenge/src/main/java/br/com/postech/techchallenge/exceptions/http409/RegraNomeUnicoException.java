package br.com.postech.techchallenge.exceptions.http409;

import br.com.postech.techchallenge.exceptions.ConstantesErro;

public final class RegraNomeUnicoException extends RegraDeNegocioException {

  public RegraNomeUnicoException(String nome) {
    super(String.format(ConstantesErro.REGRA_NOME_UNICO_VIOLADO, nome));
  }
}

