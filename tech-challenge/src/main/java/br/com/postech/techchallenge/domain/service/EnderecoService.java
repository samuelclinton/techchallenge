package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.model.Pessoa;

import java.util.List;

public interface EnderecoService {
    Endereco buscar(String codigoEndereco);
    EnderecoOutput buscarEConverterParaOutput(String codigoEndereco);
    List<EnderecoOutput> listar();
    EnderecoOutput cadastrar(Pessoa pessoa, EnderecoInput enderecoInput);
    EnderecoOutput atualizar(String codigoEndereco, EnderecoInput enderecoInput);
    void deletar(Pessoa pessoa, String codigoEndereco);

}
