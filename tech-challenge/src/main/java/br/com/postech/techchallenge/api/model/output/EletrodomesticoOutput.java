package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import lombok.Data;

import java.util.List;

@Data
public class EletrodomesticoOutput implements DomainEntityOutputModel {

    private String codigo;
    private String nome;
    private String modelo;
    private Float potencia;
    private String voltagem;
    private String marca;
    private List<PessoaResumoOutput> usuarios;

}
