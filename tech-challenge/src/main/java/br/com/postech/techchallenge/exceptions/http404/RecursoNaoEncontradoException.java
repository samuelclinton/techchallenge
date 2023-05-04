package br.com.postech.techchallenge.exceptions.http404;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}

