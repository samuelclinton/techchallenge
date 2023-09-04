package br.com.postech.techchallenge.domain.repository.filter;

import br.com.postech.techchallenge.domain.model.enums.Voltagem;
import lombok.Data;

@Data
public class EletrodomesticoFilter {

    private String nome;
    private String modelo;
    private Float potenciaMax;
    private Float potenciaMin;
    private Voltagem voltagem;
    private String marca;

}
