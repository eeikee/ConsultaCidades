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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Cidades")
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cs;

	@ApiOperation(value = "Listar todas as cidades")
	@GetMapping
	public ResponseEntity<List<Object>> listar() {
		return cs.listar();
	}

	@ApiOperation(value = "Salvar uma nova cidade")
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody @ApiParam(name = "Corpo",value = "Representação de uma nova cidade")Cidade cidade) {
		return cs.salvar(cidade);
	}

	@ApiOperation(value = "Buscar cidade pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPorID(@PathVariable("id") @ApiParam(name = "Id de uma cidade", example = "1") Long id) {
		return cs.buscarPorId(id);
	}
	
	@ApiOperation(value = "Buscar cidade pelo nome")
	@GetMapping("/cidade")
	public ResponseEntity<Object> buscarPorNomeCidade(@RequestParam @ApiParam(name = "Nome de uma cidade", example = "Sorocaba") String nome) {
		return cs.buscarPorNomeCidade(nome);
	}
	
	@ApiOperation(value = "Buscar cidade pelo nome e/ou sigla do estado no qual ela pertence")
	@GetMapping("/estado")
	public ResponseEntity<Object> buscarPorNomeSiglaEstado(@RequestParam(required = false)  @ApiParam(name = "Nome de um estado", example = "São Paulo") String nome, @RequestParam(required = false) @ApiParam(name = "Sigla de um estado", example = "SP") String sigla) {
		return cs.buscarPorNomeSiglaEstado(nome, sigla);
	}
	
	@ApiOperation(value = "Alterar cidade pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Object> alterarPorId(@PathVariable @ApiParam(name = "Id de uma cidade", example = "1") Long id, @RequestBody @ApiParam(name = "Corpo",value = "Representação de uma cidade") Cidade cidade) {
		return cs.atualizar(id, cidade);
	}
	
	@ApiOperation(value = "Deletar cidade pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable @ApiParam(name = "Id de uma cidade", example = "1") Long id){
		return cs.deletar(id);
	}
}
