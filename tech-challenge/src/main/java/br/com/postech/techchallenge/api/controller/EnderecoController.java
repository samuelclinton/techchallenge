package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.domain.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoOutput> listar() {
        return enderecoService.listar();
    }

    @GetMapping("/{codigo}")
    public EnderecoOutput buscar(@PathVariable String codigo) {
        return enderecoService.buscarEConverterParaOutput(codigo);
    }

    @PutMapping("/{codigo}")
    public EnderecoOutput atualizar(@PathVariable String codigo,
                                    @Valid @RequestBody EnderecoInput enderecoInput) {
        return enderecoService.atualizar(codigo, enderecoInput);
    }

}
