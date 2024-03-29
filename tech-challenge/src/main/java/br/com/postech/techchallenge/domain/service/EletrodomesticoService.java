package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.output.RelatorioDeCalculoDeConsumoOutput;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.repository.filter.EletrodomesticoFilter;

import java.util.List;

public interface EletrodomesticoService {
  Eletrodomestico buscar(String codigo);
  List<Eletrodomestico> pesquisar(EletrodomesticoFilter eletrodomesticoFilter);
  Eletrodomestico cadastrar(Endereco endereco, Eletrodomestico eletrodomestico);
  Eletrodomestico atualizar(String codigo, Eletrodomestico eletrodomestico);
  void deletar(String codigoEndereco, String codigoEletrodomestico);
  RelatorioDeCalculoDeConsumoOutput calcularConsumo(String codigoEletrodomestico, Integer minutosEmUso);

}
