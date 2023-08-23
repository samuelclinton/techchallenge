package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.model.Pessoa;

import java.util.List;

public interface EnderecoService {
    Endereco buscar(String codigoEndereco);
    List<Endereco> listar();
    Endereco cadastrar(Pessoa pessoa, Endereco endereco);
    Endereco atualizar(String codigoEndereco, Endereco enderecoAtualizado);
    void deletar(String codigoEndereco);
    void adicionarResidente(Endereco endereco, Pessoa residente);
    void removerResidente(Endereco endereco, Pessoa residente);

}
