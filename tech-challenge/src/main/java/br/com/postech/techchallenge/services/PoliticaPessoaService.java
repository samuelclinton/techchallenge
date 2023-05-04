package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;

public interface PoliticaPessoaService {

  PessoaDtoResponse cadastrar(PessoaDtoRequest pessoaDtoRequest);

  PessoaDtoResponse atualizar(Long id, PessoaDtoRequest pessoaDtoRequest);
}

