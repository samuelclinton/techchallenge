package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoResumoOutput;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.service.EletrodomesticoService;
import br.com.postech.techchallenge.domain.service.EnderecoService;
import br.com.postech.techchallenge.infrastructure.data.DomainEntityMapperImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enderecos/{codigoEndereco}/eletrodomesticos")
public class EnderecoEletrodomesticoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    @Autowired
    private DomainEntityMapperImpl<EletrodomesticoInput, EletrodomesticoOutput, Eletrodomestico> eletrodomesticoMapper;

    @Autowired
    private DomainEntityMapperImpl<EletrodomesticoInput, EletrodomesticoResumoOutput, Eletrodomestico> eletrodomesticoResumoMapper;

    @GetMapping
    @Transactional
    public List<EletrodomesticoResumoOutput> listar(@PathVariable String codigoEndereco) {
        final var endereco = enderecoService.buscar(codigoEndereco);
        return eletrodomesticoResumoMapper.mapearEntidadesParaListaDeOutputs(endereco.getEletrodomesticos(), EletrodomesticoResumoOutput.class);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public EletrodomesticoOutput cadastrar(@PathVariable String codigoEndereco,
                                           @Valid @RequestBody EletrodomesticoInput eletrodomesticoInput) {
        final var endereco = enderecoService.buscar(codigoEndereco);
        var novoEletrodomestico = eletrodomesticoMapper
                .mapearInputParaEntidade(eletrodomesticoInput, Eletrodomestico.class);
        novoEletrodomestico = eletrodomesticoService.cadastrar(endereco, novoEletrodomestico);
        return eletrodomesticoMapper.mapearEntidadeParaOutput(novoEletrodomestico, EletrodomesticoOutput.class);
    }

    @PutMapping("/{codigoEletrodomestico}")
    @Transactional
    public EletrodomesticoOutput atualizar(@PathVariable String codigoEletrodomestico,
                                           @Valid @RequestBody EletrodomesticoInput eletrodomesticoInput) {
        var eletrodomestico = eletrodomesticoMapper
                .mapearInputParaEntidade(eletrodomesticoInput, Eletrodomestico.class);
        eletrodomestico =  eletrodomesticoService.atualizar(codigoEletrodomestico, eletrodomestico);
        return eletrodomesticoMapper.mapearEntidadeParaOutput(eletrodomestico, EletrodomesticoOutput.class);
    }

    @DeleteMapping("/{codigoEletrodomestico}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String codigoEndereco, @PathVariable String codigoEletrodomestico) {
        eletrodomesticoService.deletar(codigoEndereco, codigoEletrodomestico);
    }

}
