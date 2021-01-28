package co.eeikee.cidadespersistapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.eeikee.cidadespersistapi.domain.Cidade;
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

	public Cidade atualizarPorId(Long id, Cidade cidade) {
		Cidade cidadeASerAtualizada = verificarExistencia(id);
		BeanUtils.copyProperties(cidade, cidadeASerAtualizada, "id");
		return cidadeASerAtualizada;
	}

	public void deletarPorId(Long id) {
		verificarExistencia(id);
		cr.deleteById(id);
	}

}
