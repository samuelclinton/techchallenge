package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.model.enums.Sexo;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AtualizarPessoaInput implements PessoaInputModel {

    @Length(max = 150)
    private String nome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    private Sexo sexo;

}
