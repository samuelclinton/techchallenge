package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.output.EnderecoResumoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Endereco;
import br.com.postech.techchallenge.domain.service.EnderecoService;
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

}
