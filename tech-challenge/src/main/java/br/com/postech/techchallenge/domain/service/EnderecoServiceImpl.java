package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.exception.EnderecoNaoEncontradoException;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.EnderecoRepository;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DomainEntityMapper<EnderecoInput, EnderecoOutput, Endereco> mapper;

    @Override
    public Endereco buscar(String codigoEndereco) {
        return enderecoRepository.findByCodigo(codigoEndereco).orElseThrow(() -> new EnderecoNaoEncontradoException(codigoEndereco));
    }

    @Override
    public EnderecoOutput buscarEConverterParaOutput(String codigoEndereco) {
        final var endereco = buscar(codigoEndereco);
        return mapper.converterEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @Override
    public List<EnderecoOutput> listar() {
        final var enderecos = enderecoRepository.findAll();
        return mapper.converterEntidadesParaListaDeOutputs(enderecos, EnderecoOutput.class);
    }

    @Override
    @Transactional
    public EnderecoOutput cadastrar(Pessoa pessoa, EnderecoInput enderecoInput) {
        final var endereco = mapper.converterInputParaEntidade(enderecoInput, Endereco.class);
        pessoa.adicionarEndereco(endereco);
        pessoaRepository.save(pessoa);
        return mapper.converterEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @Override
    @Transactional
    public EnderecoOutput atualizar(String codigoEndereco, EnderecoInput enderecoInput) {
        final var enderecoAtual = buscar(codigoEndereco);
        BeanUtils.copyProperties(enderecoInput, enderecoAtual);
        enderecoRepository.save(enderecoAtual);
        return mapper.converterEntidadeParaOutput(enderecoAtual, EnderecoOutput.class);
    }

    @Override
    @Transactional
    public void deletar(Pessoa pessoa, String codigoEndereco) {
        final var endereco = buscar(codigoEndereco);
        pessoa.removerEndereco(endereco);
        enderecoRepository.delete(endereco);
    }

}
