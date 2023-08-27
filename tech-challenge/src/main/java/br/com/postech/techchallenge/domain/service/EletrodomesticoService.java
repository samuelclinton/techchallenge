package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.model.Endereco;

import java.util.List;

public interface EletrodomesticoService {
  Eletrodomestico buscar(String codigo);
  List<Eletrodomestico> listar();
  Eletrodomestico cadastrar(Endereco endereco, Eletrodomestico eletrodomestico);
  Eletrodomestico atualizar(String codigo, Eletrodomestico eletrodomestico);
  void deletar(String codigoEndereco, String codigoEletrodomestico);

}
