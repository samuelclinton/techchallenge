package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.dtos.responses.EnderecoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EnderecoDtoRequest;
import br.com.postech.techchallenge.entities.Endereco;
import br.com.postech.techchallenge.exceptions.http404.EnderecoNaoEncontradoException;
import br.com.postech.techchallenge.repositories.EnderecoRepository;
import br.com.postech.techchallenge.utilitarios.PoliticaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PoliticaMapper<EnderecoDtoRequest, EnderecoDtoResponse, Endereco> mapper;

    @Override
    public EnderecoDtoResponse buscar(String id) {
        final var endereco = buscarOuLancarException(id);
        return mapper.converterEntidadeParaDtoResponse(endereco, EnderecoDtoResponse.class);
    }

    private Endereco buscarOuLancarException(String id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new EnderecoNaoEncontradoException(id));
    }

    @Override
    public List<EnderecoDtoResponse> listar() {
        final var enderecos = enderecoRepository.findAll();
        return mapper.converterEntidadesParaListaDeDtoResponse(enderecos, EnderecoDtoResponse.class);
    }

    @Override
    public EnderecoDtoResponse cadastrar(EnderecoDtoRequest enderecoDtoRequest) {
        final var endereco = enderecoRepository.save(mapper.converterDtoRequestParaEntidade(enderecoDtoRequest, Endereco.class));
        return mapper.converterEntidadeParaDtoResponse(endereco, EnderecoDtoResponse.class);
    }

    @Override
    public EnderecoDtoResponse atualizar(String id, EnderecoDtoRequest enderecoDtoRequest) {
        final var enderecoAtual = buscarOuLancarException(id);
        final var novoEndereco = mapper.converterDtoRequestParaEntidade(enderecoDtoRequest, Endereco.class);
        BeanUtils.copyProperties(novoEndereco, enderecoAtual);
        enderecoRepository.save(enderecoAtual);
        return mapper.converterEntidadeParaDtoResponse(enderecoAtual, EnderecoDtoResponse.class);
    }

    @Override
    public void deletar(String id) {
        final var endereco = buscarOuLancarException(id);
        enderecoRepository.delete(endereco);
    }
}
