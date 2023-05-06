package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.entities.Pessoa;
import br.com.postech.techchallenge.services.PoliticaCrudService;
import br.com.postech.techchallenge.utilitarios.PoliticaMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/pessoas")
public class PessoasControllerImpl implements PoliticaCrudController<PessoaDtoRequest, PessoaDtoResponse, PessoaFiltro, Long> {

  @Autowired
  private PoliticaCrudService<Pessoa, PessoaFiltro, Long> pessoaService;

  @Autowired
  private PoliticaMapper<PessoaDtoRequest, PessoaDtoResponse, Pessoa> pessoaMapper;

  @Override
  public ResponseEntity<PessoaDtoResponse> cadastrar(@RequestBody @Valid final PessoaDtoRequest pessoaDtoRequest,
                                                     final UriComponentsBuilder uriComponentsBuilder) {

    var response = Optional.of(pessoaDtoRequest)
        .map(dto -> this.pessoaMapper.converterDtoRequestParaEntidade(dto, Pessoa.class))
        .map(this.pessoaService::cadastrar)
        .map(entidade -> this.pessoaMapper.converterEntidadeParaDtoResponse(entidade, PessoaDtoResponse.class))
        .orElseThrow();

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/pessoas/{id}")
        .buildAndExpand(response.getId())
        .toUri())
      .body(response);
  }

  @Override
  public ResponseEntity<PessoaDtoResponse> atualizar(@PathVariable(name = "id") final Long idPessoa,
                                        @RequestBody @Valid final PessoaDtoRequest pessoaDtoRequest) {

    var response = Optional.of(pessoaDtoRequest)
      .map(dto -> this.pessoaMapper.converterDtoRequestParaEntidade(dto, Pessoa.class))
      .map(entidade -> this.pessoaService.atualizar(idPessoa, entidade))
      .map(entidade -> this.pessoaMapper.converterEntidadeParaDtoResponse(entidade, PessoaDtoResponse.class))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }

  @Override
  public ResponseEntity<?> deletar(@PathVariable(name = "id") final Long idPessoa) {

    this.pessoaService.deletar(idPessoa);

    return ResponseEntity
      .noContent()
      .build();
  }

  @Override
  public ResponseEntity<Page<PessoaDtoResponse>> pesquisar(final PessoaFiltro filtro,
    @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 20) final Pageable paginacao) {

    var response = Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pessoaService.pesquisar(parametrosDePesquisa, paginacao))
      .map(pessoas -> this.pessoaMapper.converterPaginaDeEntidadeParaPaginaDtoResponse(pessoas, PessoaDtoResponse.class))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

