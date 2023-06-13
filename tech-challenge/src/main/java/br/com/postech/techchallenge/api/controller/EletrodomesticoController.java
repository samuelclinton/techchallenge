package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.output.EletrodomesticoOutput;
import br.com.postech.techchallenge.api.model.input.EletrodomesticoInput;
import br.com.postech.techchallenge.domain.service.EletrodomesticoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    @GetMapping
    public List<EletrodomesticoOutput> listar() {
        return eletrodomesticoService.listar();
    }

    @GetMapping("/{codigo}")
    public EletrodomesticoOutput buscar(@PathVariable String codigo) {
        return eletrodomesticoService.buscar(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EletrodomesticoOutput cadastrar(@Valid @RequestBody EletrodomesticoInput eletrodomesticoInput) {
        return eletrodomesticoService.cadastrar(eletrodomesticoInput);
    }

    @PutMapping("/{codigo}")
    public EletrodomesticoOutput atualizar(@PathVariable String codigo,
                                           @Valid @RequestBody EletrodomesticoInput eletrodomesticoInput) {
        return eletrodomesticoService.atualizar(codigo, eletrodomesticoInput);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String codigo) {
        eletrodomesticoService.deletar(codigo);
    }

}
