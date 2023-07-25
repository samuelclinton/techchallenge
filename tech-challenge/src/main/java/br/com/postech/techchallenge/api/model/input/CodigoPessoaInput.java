package br.com.postech.techchallenge.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CodigoPessoaInput {

    @NotBlank
    @Size(min = 36, max = 36)
    private String codigoPessoa;

}
