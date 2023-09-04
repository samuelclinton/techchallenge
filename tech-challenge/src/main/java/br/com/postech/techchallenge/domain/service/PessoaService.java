package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.model.Pessoa;

import java.util.List;

public interface PessoaService {

  Pessoa buscar(String codigo);
  List<Pessoa> listar();
  PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput);

  PessoaOutput cadastrarFamiliar(CadastrarPessoaInput cadastrarPessoaInput, Pessoa responsavel);
  PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput);
  void deletar(String codigo);

}
