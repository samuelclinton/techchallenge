package br.com.postech.techchallenge.exceptions.http409;

import br.com.postech.techchallenge.exceptions.ConstantesErro;
import br.com.postech.techchallenge.exceptions.http404.RecursoNaoEncontradoException;

public final class RegraCpfUnicoException extends RegraDeNegocioException {

  public RegraCpfUnicoException(String cpf) {
    super(String.format(ConstantesErro.REGRA_CPF_UNICO_VIOLADA, cpf));
  }
}

