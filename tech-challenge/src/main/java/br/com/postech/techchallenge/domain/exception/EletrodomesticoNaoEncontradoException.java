package br.com.postech.techchallenge.domain.exception;

public final class EletrodomesticoNaoEncontradoException extends RecursoNaoEncontradoException {

    public EletrodomesticoNaoEncontradoException(String codigo) {
        super("Nenhum eletrodoméstico encontrado com o código " + codigo);
    }

}
