package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.controllers.filtros.PoliticaFiltro;
import br.com.postech.techchallenge.entities.PoliticaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PoliticaPessoaService<E extends PoliticaEntity, F extends PoliticaFiltro, I> {

  E cadastrar(E entidade);

  E atualizar(I id, E entidade);

  void deletar(I id);

  Page<E> pesquisar(F filtro, Pageable paginacao);
}
