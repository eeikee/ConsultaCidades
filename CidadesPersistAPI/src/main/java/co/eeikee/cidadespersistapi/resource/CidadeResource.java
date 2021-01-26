package co.eeikee.cidadespersistapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade){
		Cidade cidadeSalva = cs.salvar(cidade);
		return ResponseEntity.created(
				LocationURI.build(cidadeSalva.getId()))
				.body(cidadeSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorID(@PathVariable("id") Long id){
		return ResponseEntity.ok(cs.buscarPorId(id));
	}
}
