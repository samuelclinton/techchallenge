package br.com.postech.techchallenge.api.model.output;

import br.com.postech.techchallenge.domain.data.DomainEntityOutputModel;
import br.com.postech.techchallenge.domain.model.enums.Sexo;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class PessoaOutput implements DomainEntityOutputModel {

    private String codigo;
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String cpf;
    // private Pessoa parente;
    private Instant dataCadastro;

}
