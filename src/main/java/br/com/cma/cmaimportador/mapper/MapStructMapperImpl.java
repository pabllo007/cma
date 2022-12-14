package br.com.cma.cmaimportador.mapper;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.service.response.ArrQuotes;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public SerieHistorica baseSerieHistorica(SerieHistorica obj, AtivosEntity asset, String timeRef) {
        if (obj == null) {
            obj = new SerieHistorica();
        }
        obj.setDataReferencia(new Date());
        obj.setTicker(asset.getAsset());
        obj.setAtivos(asset);
        obj.setHoraReferencia(timeRef);

        return obj;
    }

    @Override
     public SerieHistorica ArraQuotesToSerieHistorica(ArrQuotes arrQuotes, AtivosEntity asset) {
         if (arrQuotes == null) {
             return null;
         }
         SerieHistorica obj = new SerieHistorica();
         obj.setDataReferencia(new Date());
         obj.setTicker(arrQuotes.getSymbolId().getSymbol());
         obj.setAtivos(asset);

         return obj;
     }
}
