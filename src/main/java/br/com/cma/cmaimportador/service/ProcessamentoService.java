package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.service.response.QuotesResponse;

import java.util.Date;

public interface ProcessamentoService {
    void processarEhSalvarDados(AtivosEntity asset, Date dataReferencia, QuotesResponse quotes, SerieHistorica obj);
}
