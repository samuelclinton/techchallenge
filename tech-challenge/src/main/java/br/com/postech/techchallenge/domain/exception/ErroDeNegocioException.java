package br.com.postech.techchallenge.domain.exception;

public abstract class ErroDeNegocioException extends RuntimeException {

    public ErroDeNegocioException(String message) {
        super(message);
    }

}
