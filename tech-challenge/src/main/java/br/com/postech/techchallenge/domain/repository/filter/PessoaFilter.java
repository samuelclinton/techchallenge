package br.com.postech.techchallenge.domain.repository.filter;

import br.com.postech.techchallenge.domain.model.enums.Genero;
import br.com.postech.techchallenge.domain.model.enums.TipoDeUsuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilter {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private String cpf;
    private TipoDeUsuario tipoDeUsuario;

}
