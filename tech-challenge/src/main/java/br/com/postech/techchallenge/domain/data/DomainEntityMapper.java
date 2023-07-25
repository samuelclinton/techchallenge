package br.com.postech.techchallenge.domain.data;

import br.com.postech.techchallenge.domain.model.DomainEntity;

import java.util.Collection;
import java.util.List;

public interface DomainEntityMapper<I extends DomainEntityInputModel, O extends DomainEntityOutputModel, E extends DomainEntity> {

    E mapearInputParaEntidade(I input, Class<E> entityClass);
    O mapearEntidadeParaOutput(E entity, Class<O> outputClass);
    List<O> mapearEntidadesParaListaDeOutputs(Collection<E> entities, Class<O> outputClass);

}
