package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import lombok.Data;

@Data
public class EletrodomesticoOutput implements DomainEntityOutputModel {

    private String codigo;
    private String nome;
    private String modelo;
    private String potencia;
    private String marca;

}

