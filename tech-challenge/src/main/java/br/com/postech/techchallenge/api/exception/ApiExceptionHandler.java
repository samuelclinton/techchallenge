package br.com.postech.techchallenge.api.exception;

import br.com.postech.techchallenge.api.exception.Erro.ErroValidacao;
import br.com.postech.techchallenge.domain.exception.ConflitoDeRecursoException;
import br.com.postech.techchallenge.domain.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public final class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> tratarRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {

        final var statusHttp = HttpStatus.NOT_FOUND;

        final var erro = Erro.builder()
            .status(statusHttp.value())
            .titulo("Recurso não encontrado")
            .mensagem(recursoNaoEncontradoException.getMessage())
            .build();

        return ResponseEntity.status(statusHttp).body(erro);
    }

    @ExceptionHandler(value = ConflitoDeRecursoException.class)
    public ResponseEntity<Object> tratarConflitoDeRecursoException(ConflitoDeRecursoException conflitoDeRecursoException) {

        final var statusHttp = HttpStatus.CONFLICT;

        var mensagemRetornoErro = Erro.builder()
            .status(statusHttp.value())
            .titulo("Conflito de recurso")
            .mensagem(conflitoDeRecursoException.getMessage())
            .build();

        return ResponseEntity.status(statusHttp).body(mensagemRetornoErro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers, HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        final var erro = Erro.builder()
                .status(status.value())
                .titulo("Erro de validação")
                .mensagem("Erro de validação, verifique os campos com valores inválidos e tente novamente")
                .campos(converterBindingResultParaListaDeErrosValidacao(ex.getBindingResult()))
                .build();

          return ResponseEntity.badRequest().body(erro);
    }

    private List<ErroValidacao> converterBindingResultParaListaDeErrosValidacao(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> ErroValidacao.builder()
                        .campo(fieldError.getField())
                        .motivo(fieldError.getDefaultMessage())
                        .build()
                ).toList();
    }
}

