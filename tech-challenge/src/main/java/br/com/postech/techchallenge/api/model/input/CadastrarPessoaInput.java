package br.com.postech.techchallenge.api.model.input;

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

    @NotBlank
    private String sexo;

    @CPF
    @NotBlank
    @Length(max = 14)
    private String cpf;

}
