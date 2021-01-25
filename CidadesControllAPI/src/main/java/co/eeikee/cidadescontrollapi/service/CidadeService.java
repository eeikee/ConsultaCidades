package co.eeikee.cidadescontrollapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

	public Cidade salvar(Cidade cidade) {
		return rt.exchange(ROOT_URI,
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<Cidade>(){}
				).getBody();
	}

	public Cidade buscarPorId(Long id) {
		return rt.getForObject(ROOT_URI.concat("/").concat(id.toString()),Cidade.class);
	}
}
