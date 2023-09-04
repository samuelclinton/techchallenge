package br.com.postech.techchallenge.api.model.input;

import br.com.postech.techchallenge.domain.model.enums.Genero;
import br.com.postech.techchallenge.domain.model.enums.NivelHierarquicoFamiliar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CadastrarPessoaInput implements PessoaInputModel {

    @NotBlank
    @Length(max = 150)
    private String nome;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    @NotNull
    private Genero genero;

    @CPF
    @NotBlank
    @Length(max = 11)
    private String cpf;

    @NotNull
    private NivelHierarquicoFamiliar nivelHierarquicoFamiliar;

}
