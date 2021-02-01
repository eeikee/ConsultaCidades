package co.eeikee.cidadescontrollapi.resource;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import co.eeikee.cidadescontrollapi.domain.Cidade;
import co.eeikee.cidadescontrollapi.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cs;

	@GetMapping
	public ResponseEntity<List<Object>> listar() {
		return cs.listar();
	}

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody Cidade cidade) {
		return cs.salvar(cidade);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPorID(@PathVariable("id") Long id) {
		return cs.buscarPorId(id);
	}
	
	@GetMapping("/cidade")
	public ResponseEntity<Object> buscarPorNomeCidade(@RequestParam String nome) {
		return cs.buscarPorNomeCidade(nome);
	}
	
	@GetMapping("/estado")
	public ResponseEntity<Object> buscarPorNomeCidade(@RequestParam(required = false) String nome, @RequestParam(required = false) String sigla) {
		return cs.buscarPorNomeSiglaEstado(nome, sigla);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> alterarPorId(@PathVariable Long id, @RequestBody Cidade cidade) {
		return cs.atualizar(id, cidade);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable Long id){
		return cs.deletar(id);
	}
}
