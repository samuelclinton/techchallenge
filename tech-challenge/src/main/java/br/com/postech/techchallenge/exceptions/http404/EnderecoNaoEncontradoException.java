package br.com.postech.techchallenge.exceptions.http404;

import br.com.postech.techchallenge.exceptions.ConstantesErro;

public final class EnderecoNaoEncontradoException extends RecursoNaoEncontradoException {

    public EnderecoNaoEncontradoException(String id) {
        super(String.format(ConstantesErro.ENDERECO_NAO_ENCONTRADO, id));
    }
}

