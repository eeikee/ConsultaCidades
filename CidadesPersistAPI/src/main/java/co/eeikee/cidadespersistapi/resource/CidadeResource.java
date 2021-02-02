package co.eeikee.cidadespersistapi.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import co.eeikee.cidadespersistapi.domain.Cidade;
import co.eeikee.cidadespersistapi.domain.CidadeWrapper;
import co.eeikee.cidadespersistapi.domain.Estado;
import co.eeikee.cidadespersistapi.service.CidadeService;
import co.eeikee.cidadespersistapi.service.LocationURI;
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
	public ResponseEntity<List<Cidade>> listar(){
		return !cs.listar().isEmpty() ? ResponseEntity.ok(cs.listar()) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvar uma nova cidade")
	@PostMapping
	public ResponseEntity<Void> salvarCidade(@RequestBody @Valid @ApiParam(name = "Corpo",value = "Representação de uma nova cidade")Cidade cidade){
		Cidade cidadeSalva = cs.salvar(cidade);
		return ResponseEntity.created(
				LocationURI.build(cidadeSalva.getId())).build();
	}
	
	@ApiOperation(value = "Salvar varias cidades")
	@PostMapping("/{estado}")
	public ResponseEntity<Void> salvarCidadesPorEstado(@PathVariable("estado") Estado estado, @RequestBody CidadeWrapper cidades){
		cs.salvarPorEstado(estado, cidades);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ApiOperation(value = "Buscar cidade pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorID(@PathVariable("id") @ApiParam(name = "Id de uma cidade", example = "1")Long id){
		return ResponseEntity.ok(cs.buscarPorId(id));
	}
	
	@ApiOperation(value = "Buscar cidade pelo nome")
	@GetMapping("/cidade")
	public ResponseEntity<List<Cidade>> buscarPorNomeDaCidade(@RequestParam @ApiParam(name = "Nome de uma cidade", example = "Sorocaba")String nome){
		List<Cidade> cidadesComNome = cs.buscarPorNomeCidade(nome);
		return !cidadesComNome.isEmpty() ? ResponseEntity.ok(cidadesComNome) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Buscar cidade pelo nome e/ou sigla do estado no qual ela pertence")
	@GetMapping("/estado")
	public ResponseEntity<List<Cidade>> buscarPorNomeSiglaEstado(@RequestParam(required = false)  @ApiParam(name = "Sigla de um estado", example = "SP")  String sigla , @RequestParam(required = false) @ApiParam(name = "Nome de um estado", example = "São Paulo") String nome){
		List<Cidade> resultadoBusca = new ArrayList<Cidade>();
		resultadoBusca.addAll(cs.buscarPorSiglaEstado(sigla)); 
		resultadoBusca.addAll(cs.buscarPorNomeEstado(nome));
		return !resultadoBusca.isEmpty() ? ResponseEntity.ok(resultadoBusca) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Alterar cidade pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarPorID(@PathVariable("id") @ApiParam(name = "Id de uma cidade", example = "1")Long id, @RequestBody @ApiParam(name = "Corpo",value = "Representação de uma cidade") @Valid Cidade cidade){
		cs.atualizarPorId(id, cidade);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Deletar cidade pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorID(@PathVariable("id") @ApiParam(name = "Id de uma cidade", example = "1")  Long id){
		cs.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
}
