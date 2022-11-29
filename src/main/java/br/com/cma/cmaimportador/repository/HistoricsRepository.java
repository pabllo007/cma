package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.Historics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricsRepository extends JpaRepository<Historics, Integer> {

}
