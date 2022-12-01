package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;

import java.util.List;

public interface AtivosService {
    List<AtivosEntity> getAssets();
    List<AtivosEntity> getAtivosType(String type);
}
