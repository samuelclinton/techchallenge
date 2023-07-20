package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;

import java.util.List;

public interface EletrodomesticoService {
  Eletrodomestico buscar(String codigo);
  EletrodomesticoOutput buscarEConverterParaOutput(String codigo);
  List<EletrodomesticoOutput> listar();
  EletrodomesticoOutput cadastrar(EletrodomesticoInput eletrodomesticoInput);
  EletrodomesticoOutput atualizar(String codigo, EletrodomesticoInput eletrodomesticoInput);
  void deletar(String codigo);
}
