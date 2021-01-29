package co.eeikee.cidadespersistapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.eeikee.cidadespersistapi.domain.Cidade;
import co.eeikee.cidadespersistapi.domain.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	public List<Cidade> findByNomeContaining(String nome);

	public List<Cidade> findByEstado(Estado estado);

}
