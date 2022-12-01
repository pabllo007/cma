package br.com.cma.cmaimportador.mapper;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.service.response.ArrQuotes;

public interface MapStructMapper {

    SerieHistorica ArraQuotesToSerieHistorica(ArrQuotes arrQuotes, AtivosEntity asset);
}
