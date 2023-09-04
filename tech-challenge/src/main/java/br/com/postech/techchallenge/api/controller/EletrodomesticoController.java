package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.output.EletrodomesticoResumoOutput;
import br.com.postech.techchallenge.api.model.output.RelatorioDeCalculoDeConsumoOutput;
import br.com.postech.techchallenge.domain.data.DomainEntityMapper;
import br.com.postech.techchallenge.domain.model.Eletrodomestico;
import br.com.postech.techchallenge.domain.repository.filter.EletrodomesticoFilter;
import br.com.postech.techchallenge.domain.service.EletrodomesticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public List<EletrodomesticoResumoOutput> listar(EletrodomesticoFilter eletrodomesticoFilter) {
        final var eletrodomesticos = eletrodomesticoService.pesquisar(eletrodomesticoFilter);
        return eletrodomesticoResumoMapper.mapearEntidadesParaListaDeOutputs(eletrodomesticos,
                EletrodomesticoResumoOutput.class) ;
    }

    @GetMapping("/{codigoEletrodomestico}")
    @Transactional
    public EletrodomesticoOutput buscar(@PathVariable String codigoEletrodomestico) {
        final var eletrodomestico = eletrodomesticoService.buscar(codigoEletrodomestico);
        return eletrodomesticoMapper.mapearEntidadeParaOutput(eletrodomestico, EletrodomesticoOutput.class);
    }

    @GetMapping("/{codigoEletrodomestico}/calculo-de-consumo")
    public RelatorioDeCalculoDeConsumoOutput calculoDeConsumo(@PathVariable String codigoEletrodomestico,
                                                              @RequestParam Integer minutosEmUso) {
        return eletrodomesticoService.calcularConsumo(codigoEletrodomestico, minutosEmUso);
    }

}
