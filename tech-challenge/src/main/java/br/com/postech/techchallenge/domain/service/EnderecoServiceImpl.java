package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.repository.EnderecoRepository;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.domain.exception.EnderecoNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private DomainEntityMapper<EnderecoInput, EnderecoOutput, Endereco> mapper;

    @Override
    public EnderecoOutput buscar(String id) {
        final var endereco = buscarOuLancarException(id);
        return mapper.converterEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    private Endereco buscarOuLancarException(String codigo) {
        return enderecoRepository.findByCodigo(codigo).orElseThrow(() -> new EnderecoNaoEncontradoException(codigo));
    }

    @Override
    public List<EnderecoOutput> listar() {
        final var enderecos = enderecoRepository.findAll();
        return mapper.converterEntidadesParaListaDeOutputs(enderecos, EnderecoOutput.class);
    }

    @Override
    public EnderecoOutput cadastrar(EnderecoInput enderecoInput) {
        final var endereco = enderecoRepository.save(mapper.converterInputParaEntidade(enderecoInput, Endereco.class));
        return mapper.converterEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @Override
    public EnderecoOutput atualizar(String id, EnderecoInput enderecoInput) {
        final var enderecoAtual = buscarOuLancarException(id);
        final var novoEndereco = mapper.converterInputParaEntidade(enderecoInput, Endereco.class);
        BeanUtils.copyProperties(novoEndereco, enderecoAtual, "id", "codigo");
        enderecoRepository.save(enderecoAtual);
        return mapper.converterEntidadeParaOutput(enderecoAtual, EnderecoOutput.class);
    }

    @Override
    public void deletar(String id) {
        final var endereco = buscarOuLancarException(id);
        enderecoRepository.delete(endereco);
    }
}
