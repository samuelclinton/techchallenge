package br.com.postech.techchallenge.controllers;

import br.com.postech.techchallenge.dtos.responses.EletrodomesticoDtoResponse;
import br.com.postech.techchallenge.dtos.resquests.EletrodomesticoDtoRequest;
import br.com.postech.techchallenge.services.EletrodomesticoService;
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
    public List<EletrodomesticoDtoResponse> listar() {
        return eletrodomesticoService.listar();
    }

    @GetMapping("/{id}")
    public EletrodomesticoDtoResponse buscar(@PathVariable String id) {
        return eletrodomesticoService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EletrodomesticoDtoResponse cadastrar(@Valid @RequestBody EletrodomesticoDtoRequest eletrodomesticoDtoRequest) {
        return eletrodomesticoService.cadastrar(eletrodomesticoDtoRequest);
    }

    @PutMapping("/{id}")
    public EletrodomesticoDtoResponse atualizar(@PathVariable String id, @Valid @RequestBody EletrodomesticoDtoRequest eletrodomesticoDtoRequest) {
        return eletrodomesticoService.atualizar(id, eletrodomesticoDtoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id) {
        eletrodomesticoService.deletar(id);
    }
}
