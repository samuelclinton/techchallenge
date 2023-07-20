package br.com.postech.techchallenge.domain.exception;

public final class PessoaNaoEncontradaException extends RecursoNaoEncontradoException {

    public PessoaNaoEncontradaException(String codigo) {
        super("Nenhum usuário encontrato com o código " + codigo);
    }

}
