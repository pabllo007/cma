package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.repository.AtivosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AtivosServiceImpl implements AtivosService {

    private final AtivosRepository repo;

    public AtivosServiceImpl(AtivosRepository repo) {
        this.repo = repo;
    }
    public List<AtivosEntity> getAssets() {
        List<AtivosEntity> assets = new ArrayList<>();
        repo.findAll().forEach(assets::add);
        return assets;
    }

    public List<AtivosEntity> getAtivosType(String type) {
        List<AtivosEntity> assets = new ArrayList<>();
        repo.getAtivosEntityByType(type).forEach(assets::add);
        return assets;
    }

}
