package br.com.postech.techchallenge.domain.exception;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

  public RecursoNaoEncontradoException(String mensagem) {
     super(mensagem);
  }

}
