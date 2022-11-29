package br.com.cma.cmaimportador.mapper;

import br.com.cma.cmaimportador.domain.Historics;
import br.com.cma.cmaimportador.service.response.ArrQuotes;

public interface MapStructMapper {

    Historics ArraQuotesToHistorics(ArrQuotes arrQuotes);
}
