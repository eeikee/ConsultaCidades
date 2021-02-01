package co.eeikee.cidadespersistapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.eeikee.cidadespersistapi.domain.Cidade;
import co.eeikee.cidadespersistapi.domain.Estado;
import co.eeikee.cidadespersistapi.exception.CidadeResourceExceptions.CidadeNaoEncontradaException;
import co.eeikee.cidadespersistapi.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cr;

	public List<Cidade> listar() {
		return cr.findAll();
	}

	public Cidade salvar(Cidade cidade) {
		return cr.save(cidade);
	}

	public Cidade buscarPorId(Long id) {
		return verificarExistencia(id);
	}

	public Cidade verificarExistencia(Long id) {
		try (Cidade cidade = cr.getOne(id)) {
			return cidade;
		} catch (Exception e) {
			throw new CidadeNaoEncontradaException();
		}
	}

	public void atualizarPorId(Long id, Cidade cidade) {
		Cidade cidadeASerAtualizada = verificarExistencia(id);
		BeanUtils.copyProperties(cidade, cidadeASerAtualizada, "id");
		cr.save(cidadeASerAtualizada);
	}

	public void deletarPorId(Long id) {
		verificarExistencia(id);
		cr.deleteById(id);
	}

	public List<Cidade> buscarPorNomeCidade(String nome) {
		return cr.findByNomeContaining(nome);
	}

	public List<Cidade> buscarPorSiglaEstado(String sigla) {
		if(sigla == null) {
			return new ArrayList<Cidade>();
		}
		List<Cidade> cidades = new ArrayList<Cidade>();
		Arrays.asList(Estado.values()).stream()
					.filter(estado -> estado.toString().contains(sigla.toUpperCase()))
					.forEach(estado -> cidades.addAll(cr.findByEstado(estado)));
		return cidades;
	}
	
	public List<Cidade> buscarPorNomeEstado(String nome) {
		if(nome == null) {
			return new ArrayList<Cidade>();
		}
		List<Cidade> cidades = new ArrayList<Cidade>();
		Arrays.asList(Estado.values()).stream()
					.filter(estado -> estado.getNome().toUpperCase().contains(nome.toUpperCase()))
					.forEach(estado -> cidades.addAll(cr.findByEstado(estado)));
		return cidades;
	}
}
