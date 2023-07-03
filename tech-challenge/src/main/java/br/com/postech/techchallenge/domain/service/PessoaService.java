package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;

import java.util.List;

public interface PessoaService {

  PessoaOutput buscar(String codigo);
  List<PessoaOutput> listar();
  PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput);
  PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput);
  void deletar(String codigo);
}

