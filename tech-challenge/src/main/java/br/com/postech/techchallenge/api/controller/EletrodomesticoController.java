package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoResumoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.service.EletrodomesticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    @Autowired
    private DomainEntityMapper<EletrodomesticoInput, EletrodomesticoOutput, Eletrodomestico> eletrodomesticoMapper;

    @Autowired
    private DomainEntityMapper<EletrodomesticoInput, EletrodomesticoResumoOutput, Eletrodomestico> eletrodomesticoResumoMapper;

    @GetMapping
    public List<EletrodomesticoResumoOutput> listar() {
        final var eletrodomesticos = eletrodomesticoService.listar();
        return eletrodomesticoResumoMapper.mapearEntidadesParaListaDeOutputs(eletrodomesticos,
                EletrodomesticoResumoOutput.class) ;
    }

    @GetMapping("/{codigoEletrodomestico}")
    @Transactional
    public EletrodomesticoOutput buscar(@PathVariable String codigoEletrodomestico) {
        final var eletrodomestico = eletrodomesticoService.buscar(codigoEletrodomestico);
        return eletrodomesticoMapper.mapearEntidadeParaOutput(eletrodomestico, EletrodomesticoOutput.class);
    }

}
