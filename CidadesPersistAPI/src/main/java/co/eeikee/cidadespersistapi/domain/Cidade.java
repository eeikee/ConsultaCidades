package co.eeikee.cidadespersistapi.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Cidade")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Cidade implements AutoCloseable {

	@Id
	@ApiModelProperty(value = "ID da cidade", example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "Nome da cidade", example = "Sorocaba")
	@NotEmpty(message = "O campo nome não pode estar vazio.")
	private String nome;

	
	@ApiModelProperty(value = "Estado no qual a cidade pertence", example = "SP")
	@NotNull(message = "O campo estado não pode ser nulo.")
	@Enumerated(EnumType.STRING)
	private Estado estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public void close() throws Exception {
	}

}
