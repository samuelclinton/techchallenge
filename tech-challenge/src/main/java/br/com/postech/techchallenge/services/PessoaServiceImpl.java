package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.exceptions.http404.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import br.com.postech.techchallenge.repositories.specification.PessoaSpecification;
import br.com.postech.techchallenge.utilitarios.PoliticaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PoliticaCrudService<Pessoa, PessoaFiltro, Long> {

  @Autowired
  private PessoaRepositoryJpa pessoaRepositoryJpa;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public Pessoa cadastrar(final Pessoa pessoa) {

    return Optional.of(pessoa)
      .map(entidade -> {
        entidade.setDataCadastro(Instant.now());
        return entidade;
      })
      .map(this.pessoaRepositoryJpa::save)
      .orElseThrow();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public Pessoa atualizar(final Long id, final Pessoa pessoaAtualiza) {

    return this.pessoaRepositoryJpa.findById(id)
      .map(pessoaDoDatabase -> {
        BeanUtils.copyProperties(pessoaAtualiza, pessoaDoDatabase, "id");
        pessoaDoDatabase.setDataCadastro(Instant.now());
        return pessoaDoDatabase;
      })
      .orElseThrow(() -> new PessoaNaoEncontradaException(id));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.pessoaRepositoryJpa.findById(id)
      .map(pessoa -> {
        this.pessoaRepositoryJpa.delete(pessoa);
        return true;
      })
      .orElseThrow(() -> new PessoaNaoEncontradaException(id));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
  @Override
  public Page<Pessoa> pesquisar(final PessoaFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> {
        var entidades = this.pessoaRepositoryJpa.findAll(PessoaSpecification.consultaDinamicaComFiltro(parametrosDePesquisa), paginacao);
        return entidades;
      })
      .orElseThrow();
  }
}

