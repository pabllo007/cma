package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;

public interface OpcoesService  {
    void executar(String sessionID, AtivosEntity asset, SerieHistorica serieHistorica);
}
