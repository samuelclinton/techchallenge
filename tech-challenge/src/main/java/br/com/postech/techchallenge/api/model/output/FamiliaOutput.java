package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import lombok.Data;

import java.util.Set;

@Data
public class FamiliaOutput implements DomainEntityOutputModel {

    private String codigo;
    private Set<PessoaComGeneroOutput> avos;
    private Set<PessoaComGeneroOutput> pais;
    private Set<PessoaComGeneroOutput> filhos;
    private Set<PessoaComGeneroOutput> netos;

}
