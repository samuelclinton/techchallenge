package br.com.postech.techchallenge.domain.exception;

public final class CpfExistenteException extends ConflitoDeRecursoException {

    public CpfExistenteException(String cpf) {
        super("Já existe um usuário cadastrado com o CPF " + cpf);
    }

}

