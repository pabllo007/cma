package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;
import br.com.cma.cmaimportador.repository.AssetsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AssetsServiceImpl implements AssetsService {

    private final AssetsRepository repo;

    public AssetsServiceImpl(AssetsRepository repo) {
        this.repo = repo;
    }
    public List<AssetsEntity> getAssets() {
        List<AssetsEntity> assets = new ArrayList<>();
        repo.findAll().forEach(assets::add);
        return assets;
    }

    public List<AssetsEntity> getAssetsType(String type) {
        List<AssetsEntity> assets = new ArrayList<>();
        repo.getAssetsEntityByType(type).forEach(assets::add);
        return assets;
    }

}
