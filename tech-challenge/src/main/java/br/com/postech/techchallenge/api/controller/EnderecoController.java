package br.com.postech.techchallenge.api.controller;

import br.com.postech.techchallenge.api.model.output.EnderecoOutput;
import br.com.postech.techchallenge.api.model.input.EnderecoInput;
import br.com.postech.techchallenge.domain.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return enderecoService.buscar(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoOutput cadastrar(@Valid @RequestBody EnderecoInput enderecoInput) {
        return enderecoService.cadastrar(enderecoInput);
    }

    @PutMapping("/{codigo}")
    public EnderecoOutput atualizar(@PathVariable String codigo,
                                    @Valid @RequestBody EnderecoInput enderecoInput) {
        return enderecoService.atualizar(codigo, enderecoInput);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String codigo) {
        enderecoService.deletar(codigo);
    }

}
