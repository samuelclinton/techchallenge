package br.com.postech.techchallenge.domain.data;

import br.com.postech.techchallenge.domain.model.DomainEntity;

public interface DomainEntityUtils {

    void copiarPropriedades(DomainEntity origem, DomainEntity destino, String... ignorando);

}
