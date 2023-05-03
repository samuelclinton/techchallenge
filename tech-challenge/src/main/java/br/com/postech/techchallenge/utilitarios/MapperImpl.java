package br.com.postech.techchallenge.utilitarios;

import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import br.com.postech.techchallenge.entities.PoliticaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public R converterEntidadeParaDtoResponse(E entidade, Class<R> rClass) {
    return this.modelMapper.map(entidade, rClass);
  }
}

