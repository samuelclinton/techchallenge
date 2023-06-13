package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.input.EnderecoInput;

import java.util.List;

public interface EnderecoService {
    EnderecoOutput buscar(String codigo);
    List<EnderecoOutput> listar();
    EnderecoOutput cadastrar(EnderecoInput enderecoInput);
    EnderecoOutput atualizar(String codigo, EnderecoInput enderecoInput);
    void deletar(String codigo);

}

