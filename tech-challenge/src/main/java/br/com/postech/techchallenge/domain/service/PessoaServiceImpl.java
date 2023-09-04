package br.com.postech.techchallenge.domain.service;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.input.PessoaInputModel;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.exception.CpfExistenteException;
import br.com.postech.techchallenge.domain.exception.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.domain.model.Familia;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.model.enums.TipoDeUsuario;
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
    @Transactional(readOnly = true)
    public Pessoa buscar(String codigo) {
        return pessoaRepository.findByCodigo(codigo).orElseThrow(() -> new PessoaNaoEncontradaException(codigo));
    }

    @Override
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Override
    @Transactional
    public PessoaOutput cadastrar(CadastrarPessoaInput cadastrarPessoaInput) {
        lancarExceptionCasoCPFJaCadastrado(cadastrarPessoaInput.getCpf());
        final var novaPessoa = mapper.mapearInputParaEntidade(cadastrarPessoaInput, Pessoa.class);
        novaPessoa.setTipoDeUsuario(TipoDeUsuario.RESPONSAVEL);
        final var novaFamilia = new Familia(novaPessoa);
        novaPessoa.setFamilia(novaFamilia);
        return mapper.mapearEntidadeParaOutput(pessoaRepository.save(novaPessoa), PessoaOutput.class);
    }

    @Override
    @Transactional
    public PessoaOutput cadastrarFamiliar(CadastrarPessoaInput cadastrarDependenteInput, Pessoa responsavel) {
        lancarExceptionCasoCPFJaCadastrado(cadastrarDependenteInput.getCpf());
        var dependente = mapper.mapearInputParaEntidade(cadastrarDependenteInput, Pessoa.class);
        dependente.setTipoDeUsuario(TipoDeUsuario.DEPENDENTE);
        responsavel.getFamilia().adicionarMembro(dependente);
        dependente.setFamilia(responsavel.getFamilia());
        dependente = pessoaRepository.save(dependente);
        return mapper.mapearEntidadeParaOutput(dependente, PessoaOutput.class);
    }

    @Override
    @Transactional
    public PessoaOutput atualizar(String codigo, AtualizarPessoaInput atualizarPessoaInput) {
        final var pessoaExistente = buscar(codigo);
        BeanUtils.copyProperties(atualizarPessoaInput, pessoaExistente);
        pessoaRepository.save(pessoaExistente);
        return mapper.mapearEntidadeParaOutput(pessoaExistente, PessoaOutput.class);
    }

    @Override
    @Transactional
    public void deletar(String codigoPessoa) {
        final var pessoa = buscar(codigoPessoa);

        if (pessoa.getTipoDeUsuario().equals(TipoDeUsuario.DEPENDENTE)) {
            pessoa.getFamilia().removerMembro(pessoa);
            pessoaRepository.delete(pessoa);
        } else {
            final var familiares = pessoa.getFamilia().pegarTodosOsFamiliares();
            familiares.remove(pessoa);
            familiares.forEach(familiar -> deletar(familiar.getCodigo()));
            pessoaRepository.delete(pessoa);
        }

    }

    private void lancarExceptionCasoCPFJaCadastrado(String cpf) {
        final var pessoaExistente = pessoaRepository.findByCpf(cpf);
        if (pessoaExistente.isPresent()) {
            throw new CpfExistenteException(cpf);
        }
    }

}
