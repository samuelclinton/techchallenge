package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.output.EnderecoResumoOutput;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.service.EnderecoService;
import br.com.postech.techchallenge.domain.service.PessoaService;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas/{codigoPessoa}/enderecos")
public class PessoaEnderecoController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private DomainEntityMapperImpl<EnderecoInput, EnderecoOutput, Endereco> enderecoMapper;

    @Autowired
    private DomainEntityMapperImpl<EnderecoInput, EnderecoResumoOutput, Endereco> enderecoResumoMapper;

    @GetMapping
    @Transactional
    public List<EnderecoResumoOutput> listar(@PathVariable String codigoPessoa) {
        var pessoa = pessoaService.buscar(codigoPessoa);
        var enderecos = pessoa.getEnderecos();
        return enderecoResumoMapper.mapearEntidadesParaListaDeOutputs(enderecos, EnderecoResumoOutput.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public EnderecoOutput adicionar(@PathVariable String codigoPessoa, @Valid @RequestBody EnderecoInput enderecoInput) {
        var pessoa = pessoaService.buscar(codigoPessoa);
        var endereco = enderecoResumoMapper.mapearInputParaEntidade(enderecoInput, Endereco.class);
        endereco = enderecoService.cadastrar(pessoa, endereco);
        return enderecoMapper.mapearEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @DeleteMapping("/{codigoEndereco}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void remover(@PathVariable String codigoPessoa, @PathVariable String codigoEndereco) {
        var pessoa = pessoaService.buscar(codigoPessoa);
        enderecoService.deletar(pessoa, codigoEndereco);
    }

}
