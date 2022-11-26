package br.com.cma.cmaimportador.mapper;

import br.com.cma.cmaimportador.domain.Historics;
import br.com.cma.cmaimportador.service.response.ArrQuotes;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapStructMapperImpl implements MapStructMapper {


    @Override
    public Historics QuotesResponseToHistorics(ArrQuotes arrQuotes) {

        if (arrQuotes == null) {
            return null;
        }
        return null;
    }
}
