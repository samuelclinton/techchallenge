package br.com.postech.techchallenge.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Erro {

    private Integer status;
    private final OffsetDateTime data = OffsetDateTime.now();
    private String titulo;
    private String mensagem;
    private List<ErroValidacao> campos;

    @Getter
    @Builder
    public static class ErroValidacao {
        private String campo;
        private String motivo;
    }

}

