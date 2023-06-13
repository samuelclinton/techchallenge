package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.PessoaInputModel;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.PessoaRepository;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.domain.exception.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.domain.exception.CpfExistenteException;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DomainEntityMapperImpl<PessoaInputModel, PessoaOutput, Pessoa> pessoaMapper;

    @Override
    public PessoaOutput buscar(String codigo) {
        final var pessoa = buscarOuLancarException(codigo);
        return pessoaMapper.converterEntidadeParaOutput(pessoa, PessoaOutput.class);
    }

    private Pessoa buscarOuLancarException(String codigo) {
        return pessoaRepository.findByCodigo(codigo).orElseThrow(() -> new PessoaNaoEncontradaException(codigo));
    }

    @Override
    public List<PessoaOutput> listar() {
        final var pessoas = pessoaRepository.findAll();
        return pessoaMapper.converterEntidadesParaListaDeOutputs(pessoas, PessoaOutput.class);
    }

    @Override
    public PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput) {
        final var pessoaExistente = pessoaRepository.findByCpf(cadastrarPessoaInput.getCpf());
        if (pessoaExistente.isPresent()) {
            throw new CpfExistenteException(cadastrarPessoaInput.getCpf());
        }
        final var novaPessoa = pessoaMapper.converterInputParaEntidade(cadastrarPessoaInput, Pessoa.class);
        return pessoaMapper.converterEntidadeParaOutput(pessoaRepository.save(novaPessoa), PessoaOutput.class);
    }

    @Override
    public PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput) {
        final var pessoaExistente = buscarOuLancarException(codigo);
        final var pessoaAtualizada = pessoaMapper.converterInputParaEntidade(atualizarPessoaInput, Pessoa.class);
        BeanUtils.copyProperties(pessoaAtualizada, pessoaExistente);
        pessoaRepository.save(pessoaExistente);
        return pessoaMapper.converterEntidadeParaOutput(pessoaExistente, PessoaOutput.class);
    }

    @Override
    public void deletar(String codigo) {
        final var pessoa = buscarOuLancarException(codigo);
        pessoaRepository.delete(pessoa);
    }

}

