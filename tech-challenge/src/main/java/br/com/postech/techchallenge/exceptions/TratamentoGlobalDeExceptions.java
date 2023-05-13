package br.com.postech.techchallenge.exceptions;

import br.com.postech.techchallenge.exceptions.http404.RecursoNaoEncontradoException;
import br.com.postech.techchallenge.exceptions.http409.RegraDeNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public final class TratamentoGlobalDeExceptions extends ResponseEntityExceptionHandler {

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

  @ExceptionHandler(value = RegraDeNegocioException.class)
  public ResponseEntity<Object> tratarViolacaoDeRegraDeNegocio(RegraDeNegocioException regraDeNegocioException) {

    var statusHttp = HttpStatus.CONFLICT.name();
    var mensagem = regraDeNegocioException.getMessage();
    var dataHoraDoErro = OffsetDateTime.now();

    var mensagemRetornoErro = MensagemRetornoErro.builder()
        .statusHttp(statusHttp)
        .mensagem(mensagem)
        .dataHoraDoErro(dataHoraDoErro)
      .build();

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(mensagemRetornoErro);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {

    var erros = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(erro -> erro.getDefaultMessage())
      .toList();

    return ResponseEntity
      .badRequest()
      .body(erros);
  }
}

