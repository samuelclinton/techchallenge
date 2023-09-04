package br.com.postech.techchallenge.domain.repository.filter;

import br.com.postech.techchallenge.domain.model.enums.Estado;
import lombok.Data;

@Data
public class EnderecoFilter {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private Estado estado;

}
