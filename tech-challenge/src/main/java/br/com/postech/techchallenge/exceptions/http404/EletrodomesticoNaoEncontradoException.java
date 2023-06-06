package br.com.postech.techchallenge.exceptions.http404;

import br.com.postech.techchallenge.exceptions.ConstantesErro;

public final class EletrodomesticoNaoEncontradoException extends RecursoNaoEncontradoException {

    public EletrodomesticoNaoEncontradoException(String id) {
        super(String.format(ConstantesErro.ELETRODOMESTICO_NAO_ENCONTRADO, id));
    }
}

