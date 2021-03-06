package co.eeikee.cidadescontrollapi.service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import co.eeikee.cidadescontrollapi.domain.Cidade;

@Service
public class CidadeService {
	
	@Value("${url.api.persistencia}")
	private String urlApiPersistencia;

	@Autowired
	private RestTemplate rt;

	public ResponseEntity<List<Object>> listar() {
		try {
			UriComponentsBuilder endpointListar = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades"));
			return rt.exchange(endpointListar.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
			});
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(Arrays.asList(e.getResponseBodyAsString()));
		}
	}

	public ResponseEntity<Object> salvar(Cidade cidade) {
		try {
			UriComponentsBuilder endpointSalvar = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades"));
			return rt.postForEntity(endpointSalvar.toUriString(), cidade, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(header -> header.setContentType(MediaType.APPLICATION_JSON)).body(e.getResponseBodyAsString(Charset.defaultCharset()));
		}
	}

	public ResponseEntity<Object> buscarPorId(Long id) {
		try {
			UriComponentsBuilder endpointBuscarId = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades/").concat(id.toString()));
			return rt.getForEntity(endpointBuscarId.toUriString(), Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
	
	public ResponseEntity<Object> buscarPorNomeCidade(String nome) {
		try {
			UriComponentsBuilder endpointBuscarNomeCidade = UriComponentsBuilder
					.fromHttpUrl(urlApiPersistencia.concat("/cidades/cidade"))
	                .queryParam("nome", nome);
			return rt.exchange(endpointBuscarNomeCidade.toUriString(),HttpMethod.GET, null, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
	
	public ResponseEntity<Object> buscarPorNomeSiglaEstado(String nome, String sigla) {
		try {
			UriComponentsBuilder endpointBuscarNomeSiglaEstado = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades/estado"))
	                .queryParam("nome", nome).queryParam("sigla", sigla);
			return rt.exchange(endpointBuscarNomeSiglaEstado.toUriString(),HttpMethod.GET, null, Object.class);
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
	
	public ResponseEntity<Object> atualizar(Long id, Cidade cidade) {
		try {
			UriComponentsBuilder endpointAtualizar = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades/").concat(id.toString()));
			rt.put(endpointAtualizar.toUriString(), cidade);
			return ResponseEntity.ok().build();
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(header -> header.setContentType(MediaType.APPLICATION_JSON))
					.body(e.getResponseBodyAsString());
		}
	}
	
	public ResponseEntity<Object> deletar(Long id) {
		try {
			UriComponentsBuilder endpointDelete = UriComponentsBuilder.fromHttpUrl(urlApiPersistencia.concat("/cidades/").concat(id.toString()));
			rt.delete(endpointDelete.toUriString());
			return ResponseEntity.noContent().build();
		} catch (HttpStatusCodeException e) {
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
		}
	}
}
