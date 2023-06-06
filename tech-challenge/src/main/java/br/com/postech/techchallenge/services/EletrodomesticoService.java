package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.dtos.responses.EletrodomesticoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EletrodomesticoDtoRequest;

import java.util.List;

public interface EletrodomesticoService {
  EletrodomesticoDtoResponse buscar(String id);
  List<EletrodomesticoDtoResponse> listar();
  EletrodomesticoDtoResponse cadastrar(EletrodomesticoDtoRequest eletrodomesticoDtoRequest);
  EletrodomesticoDtoResponse atualizar(String id, EletrodomesticoDtoRequest eletrodomesticoDtoRequest);
  void deletar(String id);
}

