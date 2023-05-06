package br.com.postech.techchallenge.services.padrao_regras;

import br.com.postech.techchallenge.entities.PoliticaEntity;

public interface PadraoRegrasDeNegocio<E extends PoliticaEntity> {

  void executarRegraDeNegocio(E entidade);
}

