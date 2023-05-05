package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.responses.EnderecoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EnderecoDtoRequest;
import br.com.postech.techchallenge.services.EnderecoService;
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
    public List<EnderecoDtoResponse> listar() {
        return enderecoService.listar();
    }

    @GetMapping("/{id}")
    public EnderecoDtoResponse buscar(@PathVariable String id) {
        return enderecoService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDtoResponse cadastrar(@Valid @RequestBody EnderecoDtoRequest enderecoDtoRequest) {
        return enderecoService.cadastrar(enderecoDtoRequest);
    }

    @PutMapping("/{id}")
    public EnderecoDtoResponse atualizar(@PathVariable String id, @Valid @RequestBody EnderecoDtoRequest enderecoDtoRequest) {
        return enderecoService.atualizar(id, enderecoDtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        enderecoService.deletar(id);
    }
}
