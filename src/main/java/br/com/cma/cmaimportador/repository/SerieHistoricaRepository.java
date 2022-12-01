package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.SerieHistorica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieHistoricaRepository extends JpaRepository<SerieHistorica, Integer> {

}
