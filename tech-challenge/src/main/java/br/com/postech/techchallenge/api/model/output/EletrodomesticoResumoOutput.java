package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import lombok.Data;

@Data
public class EletrodomesticoResumoOutput implements DomainEntityOutputModel {

    private String codigo;
    private String nome;
    private String modelo;
    private Float potencia;
    private String voltagem;
    private String marca;

}
