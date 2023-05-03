package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.responses.PessoaDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.PessoaDtoRequest;
import br.com.postech.techchallenge.services.PoliticaPessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/v1/pessoas")
public class PessoasControllerImpl implements PoliticaPessoaController {

  @Autowired
  private PoliticaPessoaService pessoaService;

  @Override
  public ResponseEntity<PessoaDtoResponse> cadastrar(@RequestBody @Valid final PessoaDtoRequest pessoaDtoRequest,
                                                     final UriComponentsBuilder uriComponentsBuilder) {

    var response = this.pessoaService.cadastrar(pessoaDtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/pessoas/{id}")
        .buildAndExpand(response.getId())
        .toUri())
    .body(response);
  }

}

