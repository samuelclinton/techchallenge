package br.com.postech.techchallenge.domain.exception;

public abstract class ConflitoDeRecursoException extends RuntimeException {

    public ConflitoDeRecursoException(String mensagem) {
        super(mensagem);
    }

}

