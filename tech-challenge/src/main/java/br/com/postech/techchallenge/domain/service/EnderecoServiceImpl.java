package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.domain.data.DomainEntityUtils;
import br.com.postech.techchallenge.domain.exception.EnderecoNaoEncontradoException;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.EnderecoRepository;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
import br.com.postech.techchallenge.domain.repository.filter.EnderecoFilter;
import br.com.postech.techchallenge.infrastructure.spec.EnderecoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Endereco> pesquisar(EnderecoFilter enderecoFilter) {
        return enderecoRepository.findAll(EnderecoSpecs.usandoFiltro(enderecoFilter));
    }

    @Override
    @Transactional
    public Endereco cadastrar(Pessoa pessoa, Endereco endereco) {
        pessoa.adicionarEndereco(endereco);
        endereco.setResponsavel(pessoa);
        pessoaRepository.save(pessoa);
        return endereco;
    }

    @Override
    @Transactional
    public Endereco atualizar(String codigoEndereco, Endereco endereco) {
        final var enderecoAtual = buscar(codigoEndereco);
        domainEntityUtils.copiarPropriedades(endereco, enderecoAtual,
                "responsavel", "eletrodomesticos", "residentes");
        enderecoRepository.save(enderecoAtual);
        return enderecoAtual;
    }

    @Override
    @Transactional
    public void deletar(String codigoEndereco) {
        final var endereco = buscar(codigoEndereco);
        final var residentes = new ArrayList<>(endereco.getResidentes());
        residentes.forEach(residente -> residente.removerEndereco(endereco));
        enderecoRepository.delete(endereco);
    }

    @Override
    @Transactional
    public void adicionarResidente(Endereco endereco, Pessoa residente) {
        residente.adicionarEndereco(endereco);
        var eletrodomesticos = new ArrayList<>(endereco.getEletrodomesticos());
        eletrodomesticos.forEach(eletrodomestico -> eletrodomestico.adicionarUsuario(residente));
        pessoaRepository.save(residente);
    }

    @Override
    @Transactional
    public void removerResidente(Endereco endereco, Pessoa residente) {
        residente.removerEndereco(endereco);
        var eletrodomesticos = new ArrayList<>(endereco.getEletrodomesticos());
        eletrodomesticos.forEach(eletrodomestico -> eletrodomestico.removerUsuario(residente));
        pessoaRepository.save(residente);
    }

}
