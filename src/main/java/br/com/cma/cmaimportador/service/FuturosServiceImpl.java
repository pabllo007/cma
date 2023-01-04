package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.request.QuotesRequest;
import br.com.cma.cmaimportador.service.request.RequestService;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
import br.com.cma.cmaimportador.service.response.SymbolSearchResponse;
import br.com.cma.cmaimportador.service.utils.RequestBoby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuturosServiceImpl implements FuturosService {
    private final WebClient webClient;
    private MapStructMapper mapStructMapper;
    private SerieHistoricaService serieHistoricaService;
    @Autowired
    ProcessamentoService processamentoService;
    @Autowired
    private RequestService requestService;

    @Autowired
    public FuturosServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            SerieHistoricaService serieHistoricaService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.serieHistoricaService = serieHistoricaService;
    }

    @Override
    public void executar(String sessionID, AtivosEntity asset, SerieHistorica obj) {

        Date dataReferencia = new Date();

        QuotesRequest quotesRequest = RequestBoby.montaAcoesRequest(sessionID, asset.getAsset(), "57");
        QuotesResponse quotes = requestService.getQuotesRequest(quotesRequest);

        if(obj == null) {
            new SerieHistorica();
        }

        SymbolSearchResponse  symbolSearchResponse = requestService.getSymbolSearchFututosResponse(sessionID, asset.getAsset(), 0);;

        if(symbolSearchResponse != null) {
            symbolSearchResponse.getSymbols().forEach(x -> {
                if (x.getSymbol().equals(asset.getAsset())) {
                    obj.setMarket(x.getMarket());
                    asset.setAsset(x.getSymbol());
                    processamentoService.processarEhSalvarDados(asset, dataReferencia, quotes, obj);
                }
            });
        }

    }
}
