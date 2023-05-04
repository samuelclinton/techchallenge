package br.com.postech.techchallenge.exceptions.http404;

import br.com.postech.techchallenge.exceptions.ConstantesErro;

public final class PessoaNaoEncontradaException extends RecursoNaoEncontradoException {

  public PessoaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

 public PessoaNaoEncontradaException(Long id) {
  this(String.format(ConstantesErro.PESSOA_NAO_ENCONTRADA, id));
 }
}

