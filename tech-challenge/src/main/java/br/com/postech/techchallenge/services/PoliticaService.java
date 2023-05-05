package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.controllers.filtros.PoliticaFiltro;
import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PoliticaService<Q extends PoliticaDtoRequest, R extends PoliticaDtoResponse, F extends PoliticaFiltro, I> {

  R cadastrar(Q pessoaDtoRequest);

  R atualizar(I id, Q pessoaDtoRequest);

  void deletar(I id);

  Page<R> pesquisar(F filtro, Pageable paginacao);
}

