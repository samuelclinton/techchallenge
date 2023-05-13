package br.com.postech.techchallenge.mappers;

import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import br.com.postech.techchallenge.entities.PoliticaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperImpl<T extends PoliticaDtoRequest, R extends PoliticaDtoResponse, E extends PoliticaEntity>
  implements PoliticaMapper<T, R, E> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public E converterDtoRequestParaEntidade(T dtoRequest, Class<E> eClass) {
    return this.modelMapper.map(dtoRequest, eClass);
  }

  @Override
  public List<R> converterEntidadesParaListaDeDtoResponse(List<E> entities, Class<R> rClass) {
    return entities.stream()
      .map(entidade -> this.converterEntidadeParaDtoResponse(entidade, rClass))
      .toList();
  }

  @Override
  public R converterEntidadeParaDtoResponse(E entidade, Class<R> rClass) {
    return this.modelMapper.map(entidade, rClass);
  }

  @Override
  public Page<R> converterPaginaDeEntidadeParaPaginaDtoResponse(Page<E> entidades, Class<R> eClass) {
    return entidades.map(entidade -> this.converterEntidadeParaDtoResponse(entidade, eClass));
  }
}

