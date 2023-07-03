package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import br.com.postech.techchallenge.domain.model.enums.Voltagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public final class EletrodomesticoInput implements DomainEntityInputModel {

    @NotBlank
    private String nome;

    @NotBlank
    private String modelo;

    @PositiveOrZero
    private Float potencia;

    @NotNull
    private Voltagem voltagem;

    @NotBlank
    private String marca;

}

