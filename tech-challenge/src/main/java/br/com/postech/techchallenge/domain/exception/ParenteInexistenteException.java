package br.com.postech.techchallenge.domain.exception;

public class ParenteInexistenteException extends ErroDeNegocioException {

    public ParenteInexistenteException(String message) {
        super(message);
    }

}
