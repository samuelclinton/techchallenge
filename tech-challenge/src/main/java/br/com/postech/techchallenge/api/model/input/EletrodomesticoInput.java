package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class EletrodomesticoInput implements DomainEntityInputModel {

    @NotBlank
    private String nome;

    @NotBlank
    private String modelo;

    @NotBlank
    private String potencia;

    @NotBlank
    private String marca;

}

