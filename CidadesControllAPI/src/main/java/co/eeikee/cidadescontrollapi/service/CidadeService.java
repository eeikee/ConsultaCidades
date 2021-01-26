package co.eeikee.cidadescontrollapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.eeikee.cidadescontrollapi.domain.Cidade;

@Service
public class CidadeService {
	
	private final String ROOT_URI = "http://localhost:8081/cidades"; 
	
	@Autowired
	private RestTemplate rt;
	
	public List<Cidade> listar() {
		return rt.exchange(ROOT_URI,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Cidade>>(){}
				).getBody();
	}

	public ResponseEntity<Cidade> salvar(Cidade cidade) {
		return rt.postForEntity(ROOT_URI, cidade, Cidade.class);
	}

	public ResponseEntity<Object> buscarPorId(Long id) {
		return rt.getForEntity(ROOT_URI.concat("/").concat(id.toString()), Object.class);
	}
}
