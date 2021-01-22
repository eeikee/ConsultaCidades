package co.eeikee.cidadescontrollapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.eeikee.cidadescontrollapi.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
