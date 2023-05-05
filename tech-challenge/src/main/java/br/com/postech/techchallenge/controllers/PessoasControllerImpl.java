package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.controllers.filtros.PessoaFiltro;
import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.services.PoliticaService;
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

@RestController
@RequestMapping(path = "/v1/pessoas")
public class PessoasControllerImpl implements PoliticaController<PessoaDtoRequest, PessoaDtoResponse, PessoaFiltro, Long> {

  @Autowired
  private PoliticaService pessoaService;

  @Override
  public ResponseEntity<PessoaDtoResponse> cadastrar(@RequestBody @Valid final PessoaDtoRequest pessoaDtoRequest,
                                                     final UriComponentsBuilder uriComponentsBuilder) {

    var response = this.pessoaService.cadastrar(pessoaDtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/pessoas/{id}")
        .buildAndExpand(response.getId())
        .toUri())
    .body((PessoaDtoResponse) response);
  }

  @Override
  public ResponseEntity<PessoaDtoResponse> atualizar(@PathVariable(name = "id") final Long id,
                                           @RequestBody @Valid final PessoaDtoRequest pessoaDtoRequest) {

    var response = this.pessoaService.atualizar(id, pessoaDtoRequest);

    return ResponseEntity
        .ok()
        .body((PessoaDtoResponse) response);
  }

  @Override
  public ResponseEntity<?> deletar(@PathVariable(name = "id") final Long id) {

    this.pessoaService.deletar(id);

    return ResponseEntity
        .noContent()
        .build();
  }

  @Override
  public ResponseEntity<Page<PessoaDtoResponse>> pesquisar(final PessoaFiltro filtro,
    @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 20) final Pageable paginacao) {

    var response = this.pessoaService.pesquisar(filtro, paginacao);

    return ResponseEntity
      .ok()
      .body(response);
  }
}

