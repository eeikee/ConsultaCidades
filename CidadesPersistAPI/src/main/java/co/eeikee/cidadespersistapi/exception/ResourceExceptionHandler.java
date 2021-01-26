package co.eeikee.cidadespersistapi.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.eeikee.cidadespersistapi.exception.CidadeResourceExceptions.CidadeNaoEncontradaException;
import co.eeikee.cidadespersistapi.exception.detail.ErrorDetails;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(CidadeNaoEncontradaException.class)
	public ResponseEntity<ErrorDetails> handlerCidadeNaoEncontradaException(
			CidadeNaoEncontradaException e, HttpServletRequest request){
		String msgDev = e.getCause() != null ? e.getClass().toString() : e.toString();
		ErrorDetails ed = new ErrorDetails(
				"A cidade informada não foi encontrada",
				HttpStatus.NOT_FOUND,
				LocalDateTime.now(),
				msgDev
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String msgDev = ex.getCause() != null ? ex.getClass().toString() : ex.toString();
		ErrorDetails ed = new ErrorDetails(
				"A cidade informada possui campos sem preencher ou com preenchimento incorreto.",
				HttpStatus.BAD_REQUEST,
				LocalDateTime.now(),
				msgDev);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ed);
	}
}
