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
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PoliticaService<PessoaDtoRequest, PessoaDtoResponse, PessoaFiltro, Long> {

  @Autowired
  private PoliticaMapper<PessoaDtoRequest, PessoaDtoResponse, Pessoa> mapper;

  @Autowired
  private PessoaRepositoryJpa pessoaRepositoryJpa;

  @Transactional
  @Override
  public PessoaDtoResponse cadastrar(final PessoaDtoRequest pessoaDtoRequest) {

    return Optional.of(pessoaDtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pessoa.class))
      .map(entidade -> {
        entidade.setDataCadastro(Instant.now());
        return entidade;
      })
      .map(this.pessoaRepositoryJpa::save)
      .map(entidade -> this.mapper.converterEntidadeParaDtoResponse(entidade, PessoaDtoResponse.class))
      .orElseThrow();
  }

  @Transactional
  @Override
  public PessoaDtoResponse atualizar(final Long id, final PessoaDtoRequest pessoaDtoRequest) {

    return this.pessoaRepositoryJpa.findById(id)
        .map(pessoa -> {
          var entidadeNovasInfos = this.mapper.converterDtoRequestParaEntidade(pessoaDtoRequest, Pessoa.class);
          BeanUtils.copyProperties(entidadeNovasInfos, pessoa, "id");
          pessoa.setDataCadastro(Instant.now());
          return pessoa;
        })
        .map(pessoa -> this.mapper.converterEntidadeParaDtoResponse(pessoa, PessoaDtoResponse.class))
        .orElseThrow(() -> new PessoaNaoEncontradaException(id));
  }

  @Override
  public void deletar(final Long id) {

    this.pessoaRepositoryJpa.findById(id)
      .map(pessoa -> {
        this.pessoaRepositoryJpa.delete(pessoa);
        return true;
      })
      .orElseThrow(() -> new PessoaNaoEncontradaException(id));
  }

  @Override
  public Page<PessoaDtoResponse> pesquisar(final PessoaFiltro filtro, final Pageable paginacao) {

    var entidades = this.pessoaRepositoryJpa.findAll(PessoaSpecification.consultaDinamicaComFiltro(filtro) , paginacao);
    return this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(entidades, PessoaDtoResponse.class);
  }
}

