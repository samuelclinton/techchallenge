package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.output.FamiliaOutput;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import br.com.postech.techchallenge.domain.model.Familia;
import br.com.postech.techchallenge.domain.service.PessoaService;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/pessoas/{codigoResponsavel}/familiares")
public class PessoaFamiliaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private DomainEntityMapperImpl<DomainEntityInputModel, FamiliaOutput, Familia> familiaMapper;

    @GetMapping
    @Transactional
    public FamiliaOutput familia(@PathVariable String codigoResponsavel) {
        final var pessoa = pessoaService.buscar(codigoResponsavel);
        return familiaMapper.mapearEntidadeParaOutput(pessoa.getFamilia(), FamiliaOutput.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaOutput> cadastrarFamiliar(
            @PathVariable String codigoResponsavel,
            @RequestBody @Valid CadastrarPessoaInput cadastrarPessoaInput,
            UriComponentsBuilder uriBuilder) {
        final var responsavel = pessoaService.buscar(codigoResponsavel);
        final var dependente = pessoaService.cadastrarFamiliar(cadastrarPessoaInput, responsavel);

        return ResponseEntity
          .created(uriBuilder.path("/v1/pessoas/{id}").buildAndExpand(dependente.getCodigo()).toUri())
              .body(dependente);
    }

}
