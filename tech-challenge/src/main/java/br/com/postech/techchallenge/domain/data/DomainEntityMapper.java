package br.com.postech.techchallenge.domain.data;

import br.com.postech.techchallenge.domain.model.DomainEntity;

import java.util.List;

public interface DomainEntityMapper<I extends DomainEntityInputModel, O extends DomainEntityOutputModel, E extends DomainEntity> {

    void copiarPropriedadesEntreEntidades(E sourceEntity, E targetEntity);
    E converterInputParaEntidade(I input, Class<E> entityClass);
    O converterEntidadeParaOutput(E entity, Class<O> outputClass);
    List<O> converterEntidadesParaListaDeOutputs(List<E> entities, Class<O> outputClass);

}

