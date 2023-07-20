package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.input.PessoaInputModel;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.exception.CpfExistenteException;
import br.com.postech.techchallenge.domain.exception.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DomainEntityMapperImpl<PessoaInputModel, PessoaOutput, Pessoa> mapper;

    @Override
    public Pessoa buscar(String codigo) {
        return pessoaRepository.findByCodigo(codigo).orElseThrow(() -> new PessoaNaoEncontradaException(codigo));
    }

    @Override
    public PessoaOutput buscarEConverterParaOutput(String codigo) {
        final var pessoa = buscar(codigo);
        return mapper.converterEntidadeParaOutput(pessoa, PessoaOutput.class);
    }

    @Override
    public List<PessoaOutput> listar() {
        final var pessoas = pessoaRepository.findAll();
        return mapper.converterEntidadesParaListaDeOutputs(pessoas, PessoaOutput.class);
    }

    @Override
    @Transactional
    public PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput) {
        final var pessoaExistente = pessoaRepository.findByCpf(cadastrarPessoaInput.getCpf());
        if (pessoaExistente.isPresent()) {
            throw new CpfExistenteException(cadastrarPessoaInput.getCpf());
        }
        final var novaPessoa = mapper.converterInputParaEntidade(cadastrarPessoaInput, Pessoa.class);
        return mapper.converterEntidadeParaOutput(pessoaRepository.save(novaPessoa), PessoaOutput.class);
    }

    @Override
    @Transactional
    public PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput) {
        final var pessoaExistente = buscar(codigo);
        BeanUtils.copyProperties(atualizarPessoaInput, pessoaExistente);
        pessoaRepository.save(pessoaExistente);
        return mapper.converterEntidadeParaOutput(pessoaExistente, PessoaOutput.class);
    }

    @Override
    @Transactional
    public void deletar(String codigo) {
        final var pessoa = buscar(codigo);
        pessoaRepository.delete(pessoa);
    }

}
