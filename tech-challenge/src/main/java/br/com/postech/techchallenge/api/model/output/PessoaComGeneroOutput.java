package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import br.com.postech.techchallenge.domain.model.enums.Genero;
import lombok.Data;

@Data
public class PessoaComGeneroOutput implements DomainEntityOutputModel {

    private String codigo;
    private String nome;
    private Genero genero;

}
