package br.com.postech.techchallenge.utilitarios;

import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import br.com.postech.techchallenge.entities.PoliticaEntity;

public interface PoliticaMapper<T extends PoliticaDtoRequest, R extends PoliticaDtoResponse, E extends PoliticaEntity> {

    E converterDtoRequestParaEntidade(T dtoRequest, Class<E> eClass);

    R converterEntidadeParaDtoResponse(E entity, Class<R> eClass);
}

