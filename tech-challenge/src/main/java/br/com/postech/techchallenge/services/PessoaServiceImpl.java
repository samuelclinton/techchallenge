package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.exceptions.ConstantesErro;
import br.com.postech.techchallenge.exceptions.http404.PessoaNaoEncontradaException;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import br.com.postech.techchallenge.repositories.specification.PessoaSpecification;
import br.com.postech.techchallenge.services.padrao_regras.PadraoRegrasDeNegocio;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class PessoaServiceImpl implements PoliticaPessoaService<Pessoa, PessoaFiltro, Long> {

  @Autowired
  private PessoaRepositoryJpa pessoaRepositoryJpa;

  @Autowired
  private List<PadraoRegrasDeNegocio> regrasDeNegocios;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public Pessoa cadastrar(final Pessoa pessoa) {

    return Optional.of(pessoa)
      .map(entidade -> {
        this.regrasDeNegocios.forEach(regra -> regra.executarRegraDeNegocio(entidade));
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
        this.regrasDeNegocios.forEach(regra -> regra.executarRegraDeNegocio(pessoaAtualiza));
        BeanUtils.copyProperties(pessoaAtualiza, pessoaDoDatabase, "id");
        pessoaDoDatabase.setDataCadastro(Instant.now());
        return pessoaDoDatabase;
      })
      .orElseThrow(() -> {
        log.info(String.format(ConstantesErro.PESSOA_NAO_ENCONTRADA, id));
        throw new PessoaNaoEncontradaException(id);
      });
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.pessoaRepositoryJpa.findById(id)
      .map(pessoa -> {
        this.pessoaRepositoryJpa.delete(pessoa);
        return true;
      })
      .orElseThrow(() -> {
          log.info(String.format(ConstantesErro.PESSOA_NAO_ENCONTRADA, id));
          throw new PessoaNaoEncontradaException(id);
      });
  }

  @Transactional(readOnly = true)
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

