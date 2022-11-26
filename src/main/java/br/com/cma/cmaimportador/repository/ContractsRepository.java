package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.ContractsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractsRepository extends JpaRepository<ContractsEntity, Integer> {
}
