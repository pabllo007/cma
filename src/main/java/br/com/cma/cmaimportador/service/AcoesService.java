package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;

public interface AcoesService {
    void executar(String sessionID, AtivosEntity asset, SerieHistorica serieHistorica, String sourceId);
}
