package co.eeikee.cidadespersistapi.exception.detail;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorDetails {

	private String titulo;
	private HttpStatus status;
	private LocalDateTime momento;
	
	@JsonInclude(Include.NON_NULL)
	private String mensagemDev;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getMomento() {
		return momento;
	}

	public void setMomento(LocalDateTime momento) {
		this.momento = momento;
	}

	public String getMensagemDev() {
		return mensagemDev;
	}

	public void setMensagemDev(String mensagemDev) {
		this.mensagemDev = mensagemDev;
	}

	public ErrorDetails(String titulo, HttpStatus status, LocalDateTime momento) {
		this.titulo = titulo;
		this.status = status;
		this.momento = momento;
	}

	public ErrorDetails(String titulo, HttpStatus status, LocalDateTime momento, String mensagemDev) {
		this.titulo = titulo;
		this.status = status;
		this.momento = momento;
		this.mensagemDev = mensagemDev;
	}
}
