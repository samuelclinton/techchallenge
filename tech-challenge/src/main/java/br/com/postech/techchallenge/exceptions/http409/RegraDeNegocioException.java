package br.com.postech.techchallenge.exceptions.http409;

public abstract class RegraDeNegocioException extends RuntimeException {

  public RegraDeNegocioException(String mensagem) {
    super(mensagem);
  }
}

