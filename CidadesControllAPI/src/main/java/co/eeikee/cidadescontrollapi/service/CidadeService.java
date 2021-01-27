package co.eeikee.cidadescontrollapi.service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.eeikee.cidadescontrollapi.domain.Cidade;

@Service
public class CidadeService {

	private final String ROOT_URI = "http://localhost:8081/cidades";

	@Autowired
	private RestTemplate rt;

	public ResponseEntity<List<Object>> listar() {
		try {
			return rt.exchange(ROOT_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
			});
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(Arrays.asList(e.getResponseBodyAsString()));
		}
	}

	public ResponseEntity<Object> salvar(Cidade cidade) {
		try {
			return rt.postForEntity(ROOT_URI, cidade, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(header -> header.setContentType(MediaType.APPLICATION_JSON)).body(e.getResponseBodyAsString(Charset.defaultCharset()));
		}
	}

	public ResponseEntity<Object> buscarPorId(Long id) {
		try {
			return rt.getForEntity(ROOT_URI.concat("/").concat(id.toString()), Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
}
