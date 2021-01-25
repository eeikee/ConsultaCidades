package co.eeikee.cidadescontrollapi.domain;

public class Cidade {
	
	private Long id;
	
	private String nome;
	
	private Estado sigla;

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

	public Estado getSigla() {
		return sigla;
	}

	public void setSigla(Estado sigla) {
		this.sigla = sigla;
	}
}
