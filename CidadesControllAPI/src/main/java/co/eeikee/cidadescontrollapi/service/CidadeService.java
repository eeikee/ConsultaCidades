package co.eeikee.cidadescontrollapi.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.eeikee.cidadescontrollapi.domain.Cidade;

@Service
public class CidadeService {
	
	private RestTemplate rt;
	private final String URL_BASE = "localhost:8081/cidades"; 
	
	public List<Cidade> listar() {
		return rt.exchange(URL_BASE,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Cidade>>(){}
				).getBody();
	}

	public Cidade salvar(Cidade cidade) {
		return rt.exchange(URL_BASE,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<Cidade>(){}
				).getBody();
	}

	public Cidade buscarPorId(Long id) {
		return rt.exchange(URL_BASE.concat(id.toString()),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Cidade>(){}
				).getBody();
	}
}
