package co.eeikee.cidadespersistapi.resource;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.eeikee.cidadespersistapi.domain.Cidade;
import co.eeikee.cidadespersistapi.service.CidadeService;
import co.eeikee.cidadespersistapi.service.LocationURI;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cs;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listar(){
		return !cs.listar().isEmpty() ? ResponseEntity.ok(cs.listar()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid Cidade cidade){
		Cidade cidadeSalva = cs.salvar(cidade);
		return ResponseEntity.created(
				LocationURI.build(cidadeSalva.getId())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorID(@PathVariable("id") Long id){
		return ResponseEntity.ok(cs.buscarPorId(id));
	}
	
	@GetMapping("/cidade")
	public ResponseEntity<List<Cidade>> buscarPorNomeDaCidade(@RequestParam String nome){
		List<Cidade> cidadesComNome = cs.buscarPorNomeCidade(nome);
		return !cidadesComNome.isEmpty() ? ResponseEntity.ok(cidadesComNome) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/estado?{pesquisa}")
	public ResponseEntity<List<Cidade>> buscarPorSiglaDoEstado(@PathVariable("pesquisa") String sigla){
		List<Cidade> estadoComSigla = cs.buscarPorSiglaEstado(sigla);
		return !estadoComSigla.isEmpty() ? ResponseEntity.ok(estadoComSigla) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/estado/nome")
	public ResponseEntity<List<Cidade>> buscarPorNomeDoEstado(@RequestParam String nome){
		List<Cidade> estadoComSigla = cs.buscarPorNomeEstado(nome);
		return !estadoComSigla.isEmpty() ? ResponseEntity.ok(estadoComSigla) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizarPorID(@PathVariable("id") Long id, @RequestBody @Valid Cidade cidade){
		return ResponseEntity.ok(cs.atualizarPorId(id, cidade));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorID(@PathVariable("id") Long id){
		cs.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
