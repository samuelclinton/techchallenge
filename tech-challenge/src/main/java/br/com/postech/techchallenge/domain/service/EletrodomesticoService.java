package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;

import java.util.List;

public interface EletrodomesticoService {
  EletrodomesticoOutput buscar(String codigo);
  List<EletrodomesticoOutput> listar();
  EletrodomesticoOutput cadastrar(EletrodomesticoInput eletrodomesticoInput);
  EletrodomesticoOutput atualizar(String codigo, EletrodomesticoInput eletrodomesticoInput);
  void deletar(String codigo);
}

