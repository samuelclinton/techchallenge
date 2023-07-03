package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.data.DomainEntityInputModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParenteInput implements DomainEntityInputModel {

    @NotBlank
    private String codigo;

}
