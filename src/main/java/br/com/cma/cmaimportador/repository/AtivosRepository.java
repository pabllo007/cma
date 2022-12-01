package br.com.cma.cmaimportador.repository;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtivosRepository extends JpaRepository<AtivosEntity, Integer> {
    List<AtivosEntity> getAtivosEntityByType(String type);
}
