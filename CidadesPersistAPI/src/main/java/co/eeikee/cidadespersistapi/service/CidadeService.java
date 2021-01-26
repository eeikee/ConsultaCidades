package co.eeikee.cidadespersistapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.eeikee.cidadespersistapi.domain.Cidade;
import co.eeikee.cidadespersistapi.exception.CidadeResourceExceptions.CidadeNaoEncontradaException;
import co.eeikee.cidadespersistapi.repository.CidadeRepository;

@Service
public class CidadeService{

	@Autowired
	private CidadeRepository cr;
	
	public List<Cidade> listar() {
		return cr.findAll();
	} 

	public Cidade salvar(Cidade cidade) {
		return cr.save(cidade);
	
	}

	public Cidade buscarPorId(Long id) {
		try(Cidade cidade = cr.getOne(id)){
			return cidade;
		} catch (Exception e) {
			throw new CidadeNaoEncontradaException();
		}
	}
}
