package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoOutput implements DomainEntityOutputModel {

    private String codigo;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private PessoaResumoOutput responsavel;
    private List<PessoaResumoOutput> residentes;

}
