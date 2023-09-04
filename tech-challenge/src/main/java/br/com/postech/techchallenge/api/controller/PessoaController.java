package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.AtualizarPessoaInput;
import br.com.postech.techchallenge.api.model.input.CadastrarPessoaInput;
import br.com.postech.techchallenge.api.model.input.PessoaInputModel;
import br.com.postech.techchallenge.api.model.output.PessoaOutput;
import br.com.postech.techchallenge.domain.model.Pessoa;
import br.com.postech.techchallenge.domain.repository.filter.PessoaFilter;
import br.com.postech.techchallenge.domain.service.PessoaService;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private DomainEntityMapperImpl<PessoaInputModel, PessoaOutput, Pessoa> pessoaMapper;

    @GetMapping
    public List<PessoaOutput> pesquisar(PessoaFilter pessoaFilter) {
        final var pessoas = pessoaService.pesquisar(pessoaFilter);
        return pessoaMapper.mapearEntidadesParaListaDeOutputs(pessoas, PessoaOutput.class);
    }

    @GetMapping("/{codigoPessoa}")
    public PessoaOutput buscarPorCodigo(@PathVariable String codigoPessoa) {
        final var pessoa = pessoaService.buscar(codigoPessoa);
        return pessoaMapper.mapearEntidadeParaOutput(pessoa, PessoaOutput.class);
    }

    @PostMapping
    public ResponseEntity<PessoaOutput> cadastrar(@RequestBody @Valid CadastrarPessoaInput cadastrarPessoaInput,
                                                  UriComponentsBuilder uriBuilder) {

      final var pessoa = pessoaService.cadastrar(cadastrarPessoaInput);
      return ResponseEntity
          .created(uriBuilder.path("/v1/pessoas/{id}").buildAndExpand(pessoa.getCodigo()).toUri())
              .body(pessoa);
    }

    @PutMapping("/{codigo}")
    public PessoaOutput atualizar(@PathVariable String codigo,
                                  @RequestBody @Valid AtualizarPessoaInput atualizarPessoaInput) {

        return pessoaService.atualizar(codigo, atualizarPessoaInput);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String codigo) {
        pessoaService.deletar(codigo);
    }

}
