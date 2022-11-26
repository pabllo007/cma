package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;

import java.util.List;

public interface AssetsService {
    List<AssetsEntity> getAssets();
    List<AssetsEntity> getAssetsType(String type);
}
