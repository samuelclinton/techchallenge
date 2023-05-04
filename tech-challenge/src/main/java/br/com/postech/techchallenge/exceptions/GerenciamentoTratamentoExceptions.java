package br.com.postech.techchallenge.exceptions;

import br.com.postech.techchallenge.exceptions.http404.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public final class GerenciamentoTratamentoExceptions {

  @ExceptionHandler(value = RecursoNaoEncontradoException.class)
  public ResponseEntity<Object> tratarRecursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontradoException) {

    var statusHttp = HttpStatus.NOT_FOUND.name();
    var mensagem = recursoNaoEncontradoException.getMessage();
    var dataHoraDoErro = OffsetDateTime.now();

    var mensagemRetornoErro = MensagemRetornoErro.builder()
        .statusHttp(statusHttp)
        .mensagem(mensagem)
        .dataHoraDoErro(dataHoraDoErro)
      .build();

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(mensagemRetornoErro);
  }
}

