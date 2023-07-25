package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.domain.data.DomainEntityUtils;
import br.com.postech.techchallenge.domain.exception.EnderecoNaoEncontradoException;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.EnderecoRepository;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
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
    private DomainEntityUtils domainEntityUtils;

    @Override
    public Endereco buscar(String codigoEndereco) {
        return enderecoRepository.findByCodigo(codigoEndereco).orElseThrow(() -> new EnderecoNaoEncontradoException(codigoEndereco));
    }

    @Override
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    @Override
    @Transactional
    public Endereco cadastrar(Pessoa pessoa, Endereco endereco) {
        pessoa.adicionarEndereco(endereco);
        pessoaRepository.save(pessoa);
        return endereco;
    }

    @Override
    @Transactional
    public Endereco atualizar(String codigoEndereco, Endereco endereco) {
        final var enderecoAtual = buscar(codigoEndereco);
        domainEntityUtils.copiarPropriedades(endereco, enderecoAtual, "residentes");
        enderecoRepository.save(enderecoAtual);
        return enderecoAtual;
    }

    @Override
    @Transactional
    public void deletar(Pessoa pessoa, String codigoEndereco) {
        final var endereco = buscar(codigoEndereco);
        pessoa.removerEndereco(endereco);
        enderecoRepository.delete(endereco);
    }

    @Override
    @Transactional
    public void adicionarResidente(Endereco endereco, Pessoa pessoa) {
        pessoa.adicionarEndereco(endereco);
        pessoaRepository.save(pessoa);
    }

    @Override
    @Transactional
    public void removerResidente(Endereco endereco, Pessoa pessoa) {
        pessoa.removerEndereco(endereco);
        pessoaRepository.save(pessoa);
    }

}
