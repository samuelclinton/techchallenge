package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface PoliticaPessoaController {

  @PostMapping
  ResponseEntity<PessoaDtoResponse> cadastrar(PessoaDtoRequest pessoaDtoRequest, UriComponentsBuilder uriComponentsBuilder);
}

