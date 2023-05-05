package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.dtos.responses.EnderecoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EnderecoDtoRequest;

import java.util.List;

public interface EnderecoService {
  EnderecoDtoResponse buscar(String id);
  List<EnderecoDtoResponse> listar();
  EnderecoDtoResponse cadastrar(EnderecoDtoRequest enderecoDtoRequest);
  EnderecoDtoResponse atualizar(String id, EnderecoDtoRequest enderecoDtoRequest);
  void deletar(String id);
}

