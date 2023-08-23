package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoResumoOutput;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.service.PessoaService;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas/{codigoPessoa}/enderecos")
public class PessoaEnderecoController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private DomainEntityMapperImpl<EnderecoInput, EnderecoResumoOutput, Endereco> enderecoResumoMapper;

    @GetMapping
    @Transactional
    public List<EnderecoResumoOutput> listar(@PathVariable String codigoPessoa) {
        var pessoa = pessoaService.buscar(codigoPessoa);
        var enderecos = pessoa.getEnderecos();
        return enderecoResumoMapper.mapearEntidadesParaListaDeOutputs(enderecos, EnderecoResumoOutput.class);
    }

}
