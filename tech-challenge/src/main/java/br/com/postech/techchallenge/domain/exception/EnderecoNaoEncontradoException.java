package br.com.postech.techchallenge.domain.exception;

public final class EnderecoNaoEncontradoException extends RecursoNaoEncontradoException {

    public EnderecoNaoEncontradoException(String codigo) {
        super("Nenhum endereço encontrado com o codigo " + codigo);
    }

}

