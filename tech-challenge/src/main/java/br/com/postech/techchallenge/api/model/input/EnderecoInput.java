package br.com.postech.techchallenge.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public final class EnderecoInput implements EnderecoInputModel {

    @NotBlank
    private String rua;

    @NotBlank
    @Length(max = 16)
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    @Length(max = 2)
    private String estado;

    @NotBlank
    @Length(min = 36, max = 36)
    private String codigoResponsavel;

}
