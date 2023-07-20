package br.com.postech.techchallenge.infrastructure.data;

import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import br.com.postech.techchallenge.domain.model.DomainEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DomainEntityMapperImpl<I extends DomainEntityInputModel, O extends DomainEntityOutputModel, E extends DomainEntity>
    implements DomainEntityMapper<I, O, E> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public E converterInputParaEntidade(I input, Class<E> entityClass) {
        return this.modelMapper.map(input, entityClass);
    }

    @Override
    public List<O> converterEntidadesParaListaDeOutputs(Collection<E> entities, Class<O> outputClass) {
        return entities.stream()
          .map(entidade -> this.converterEntidadeParaOutput(entidade, outputClass))
          .toList();
    }

    @Override
    public O converterEntidadeParaOutput(E entidade, Class<O> outputClass) {
        return this.modelMapper.map(entidade, outputClass);
    }
}
