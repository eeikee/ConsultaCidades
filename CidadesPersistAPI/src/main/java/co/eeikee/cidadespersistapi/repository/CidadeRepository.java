package co.eeikee.cidadespersistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.eeikee.cidadespersistapi.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
