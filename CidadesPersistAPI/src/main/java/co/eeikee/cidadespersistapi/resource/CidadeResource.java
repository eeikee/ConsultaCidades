package co.eeikee.cidadespersistapi.resource;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	@GetMapping("/estado")
	public ResponseEntity<List<Cidade>> buscarPorSiglaDoEstado(@RequestParam(required = false) String sigla , @RequestParam(required = false) String nome){
		List<Cidade> resultadoBusca = new ArrayList<Cidade>();
		resultadoBusca.addAll(cs.buscarPorSiglaEstado(sigla)); 
		resultadoBusca.addAll(cs.buscarPorNomeEstado(nome));
		return !resultadoBusca.isEmpty() ? ResponseEntity.ok(resultadoBusca) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarPorID(@PathVariable("id") Long id, @RequestBody @Valid Cidade cidade){
		cs.atualizarPorId(id, cidade);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorID(@PathVariable("id") Long id){
		cs.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
