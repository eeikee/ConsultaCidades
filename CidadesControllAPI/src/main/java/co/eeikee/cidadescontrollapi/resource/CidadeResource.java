package co.eeikee.cidadescontrollapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.eeikee.cidadescontrollapi.domain.Cidade;
import co.eeikee.cidadescontrollapi.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cs;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listar(){
		return ResponseEntity.ok(cs.listar());
	}
	
	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade){
		return ResponseEntity.ok(cs.salvar(cidade));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorID(@PathVariable("id") Long id){
		return ResponseEntity.ok(cs.buscarPorId(id));
	}
}
