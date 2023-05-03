package br.com.postech.techchallenge.services;

import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.repositories.PessoaRepositoryJpa;
import br.com.postech.techchallenge.utilitarios.PoliticaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PoliticaPessoaService {

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

}

