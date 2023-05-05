package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.controllers.filtros.PoliticaFiltro;
import br.com.postech.techchallenge.dtos.responses.PoliticaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PoliticaDtoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface PoliticaController<Q extends PoliticaDtoRequest, R extends PoliticaDtoResponse, F extends PoliticaFiltro, I> {

  @PostMapping
  ResponseEntity<R> cadastrar(Q pessoaDtoRequest, UriComponentsBuilder uriComponentsBuilder);

  @PutMapping(path = "/{id}")
  ResponseEntity<R> atualizar(I id, Q pessoaDtoRequest);

  @DeleteMapping(path = "/{id}")
  ResponseEntity<?> deletar(I id);

  @GetMapping
  ResponseEntity<Page<R>> pesquisar(F filtro, Pageable paginacao);
}

