package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.BidsAsksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidsAsksRepository extends JpaRepository<BidsAsksEntity, Integer> {

}
