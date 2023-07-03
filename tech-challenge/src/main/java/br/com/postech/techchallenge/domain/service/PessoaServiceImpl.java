package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.input.PessoaInputModel;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.exception.CpfExistenteException;
import br.com.postech.techchallenge.domain.exception.ParenteInexistenteException;
import br.com.postech.techchallenge.domain.exception.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DomainEntityMapperImpl<PessoaInputModel, PessoaOutput, Pessoa> mapper;

    @Override
    public PessoaOutput buscar(String codigo) {
        final var pessoa = buscarOuLancarException(codigo);
        return mapper.converterEntidadeParaOutput(pessoa, PessoaOutput.class);
    }

    private Pessoa buscarOuLancarException(String codigo) {
        return pessoaRepository.findByCodigo(codigo).orElseThrow(() -> new PessoaNaoEncontradaException(codigo));
    }

    @Override
    public List<PessoaOutput> listar() {
        final var pessoas = pessoaRepository.findAll();
        return mapper.converterEntidadesParaListaDeOutputs(pessoas, PessoaOutput.class);
    }

    @Override
    public PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput) {
        final var pessoaExistente = pessoaRepository.findByCpf(cadastrarPessoaInput.getCpf());
        if (pessoaExistente.isPresent()) {
            throw new CpfExistenteException(cadastrarPessoaInput.getCpf());
        }
        final var novaPessoa = mapper.converterInputParaEntidade(cadastrarPessoaInput, Pessoa.class);

        if (cadastrarPessoaInput.getParente() != null) {
            final var parente = buscarParenteExistente(cadastrarPessoaInput.getParente().getCodigo());
            novaPessoa.setParente(parente);
        }

        return mapper.converterEntidadeParaOutput(pessoaRepository.save(novaPessoa), PessoaOutput.class);
    }

    @Override
    public PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput) {
        final var pessoaExistente = buscarOuLancarException(codigo);
        final var pessoaAtualizada = mapper.converterInputParaEntidade(atualizarPessoaInput, Pessoa.class);

        if (atualizarPessoaInput.getParente() != null) {
            final var parente = buscarParenteExistente(atualizarPessoaInput.getParente().getCodigo());
            pessoaAtualizada.setParente(parente);
        }

        mapper.copiarPropriedadesEntreEntidades(pessoaAtualizada, pessoaExistente);
        pessoaRepository.save(pessoaExistente);
        return mapper.converterEntidadeParaOutput(pessoaExistente, PessoaOutput.class);
    }

    @Override
    public void deletar(String codigo) {
        final var pessoa = buscarOuLancarException(codigo);
        final var parentes = pessoaRepository.findAllByParente(pessoa);
        if (!parentes.isEmpty()) {
            pessoaRepository.deleteAll(parentes);
        }
        pessoaRepository.delete(pessoa);
    }

    private Pessoa buscarParenteExistente(String codigoDoParente) {
        try {
            return buscarOuLancarException(codigoDoParente);
        } catch (PessoaNaoEncontradaException e) {
            throw new ParenteInexistenteException(String.format(
                    "Parente inexistente, nenhum usuário encontrado com o código %s,", codigoDoParente));
        }
    }

}

