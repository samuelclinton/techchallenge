package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
public final class EnderecoInput implements DomainEntityInputModel {

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

}
