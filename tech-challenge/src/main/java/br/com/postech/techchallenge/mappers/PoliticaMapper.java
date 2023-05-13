package br.com.postech.techchallenge.mappers;

import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import br.com.postech.techchallenge.entities.PoliticaEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PoliticaMapper<T extends PoliticaDtoRequest, R extends PoliticaDtoResponse, E extends PoliticaEntity> {

    E converterDtoRequestParaEntidade(T dtoRequest, Class<E> eClass);

    List<R> converterEntidadesParaListaDeDtoResponse(List<E> entities, Class<R> eClass);

    R converterEntidadeParaDtoResponse(E entity, Class<R> eClass);

    Page<R> converterPaginaDeEntidadeParaPaginaDtoResponse(Page<E> entidades, Class<R> eClass);
}

