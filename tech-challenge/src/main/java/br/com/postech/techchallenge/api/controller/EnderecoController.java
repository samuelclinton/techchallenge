package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.CodigoPessoaInput;
import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.output.EnderecoResumoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.service.EnderecoService;
import br.com.postech.techchallenge.domain.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private DomainEntityMapper<EnderecoInput, EnderecoOutput, Endereco> enderecoMapper;

    @Autowired
    private DomainEntityMapper<EnderecoInput, EnderecoResumoOutput, Endereco> enderecoResumoMapper;

    @GetMapping
    public List<EnderecoResumoOutput> listar() {
        var enderecos = enderecoService.listar();
        return enderecoResumoMapper.mapearEntidadesParaListaDeOutputs(enderecos, EnderecoResumoOutput.class);
    }

    @GetMapping("/{codigoEndereco}")
    @Transactional
    public EnderecoOutput buscar(@PathVariable String codigoEndereco) {
        var endereco = enderecoService.buscar(codigoEndereco);
        return enderecoMapper.mapearEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @PutMapping("/{codigoEndereco}")
    @Transactional
    public EnderecoOutput atualizar(@PathVariable String codigoEndereco,
                                    @Valid @RequestBody EnderecoInput enderecoInput) {
        var enderecoAtualizado = enderecoMapper.mapearInputParaEntidade(enderecoInput, Endereco.class);
        enderecoAtualizado = enderecoService.atualizar(codigoEndereco, enderecoAtualizado);
        return enderecoMapper.mapearEntidadeParaOutput(enderecoAtualizado, EnderecoOutput.class);
    }

    @PutMapping("/{codigoEndereco}/residentes")
    @Transactional
    public EnderecoOutput adicionarResidente(@PathVariable String codigoEndereco,
                                             @Valid @RequestBody CodigoPessoaInput codigoPessoaInput) {
        var endereco = enderecoService.buscar(codigoEndereco);
        var pessoa = pessoaService.buscar(codigoPessoaInput.getCodigoPessoa());
        enderecoService.adicionarResidente(endereco, pessoa);
        return enderecoMapper.mapearEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

    @DeleteMapping("/{codigoEndereco}/residentes/{codigoPessoa}")
    @Transactional
    public EnderecoOutput removerResidente(@PathVariable String codigoEndereco,
                                           @PathVariable String codigoPessoa) {
        var endereco = enderecoService.buscar(codigoEndereco);
        var pessoa = pessoaService.buscar(codigoPessoa);
        enderecoService.removerResidente(endereco, pessoa);
        return enderecoMapper.mapearEntidadeParaOutput(endereco, EnderecoOutput.class);
    }

}
