package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.AssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsRepository extends JpaRepository<AssetsEntity, Integer> {

    List<AssetsEntity> getAssetsEntityByType(String type);
}
