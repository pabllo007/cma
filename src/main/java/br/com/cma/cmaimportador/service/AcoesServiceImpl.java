package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.BidsAsksEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.request.QuotesRequest;
import br.com.cma.cmaimportador.service.request.RequestService;
import br.com.cma.cmaimportador.service.response.ArrQuotes;
import br.com.cma.cmaimportador.service.response.ArrValues;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
import br.com.cma.cmaimportador.service.utils.RequestBoby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcoesServiceImpl implements AcoesService {
    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    private SerieHistoricaService serieHistoricaService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProcessamentoService processamentoService;

    @Autowired
    public AcoesServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            SerieHistoricaService serieHistoricaService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.serieHistoricaService = serieHistoricaService;
    }

    @Override
    public void executar(String sessionID, AtivosEntity asset, SerieHistorica obj, String sourceId) {

        Date dataReferencia = new Date();

        QuotesRequest quotesRequest = RequestBoby.montaAcoesRequest(sessionID, asset.getAsset(), sourceId);
        QuotesResponse quotes = requestService.getQuotesRequest(quotesRequest);

        if (quotes.getArrQuotes() == null) {
            log.info("N??O RETORNOU INFORMA????ES NO ArrQuotes");
            log.info(quotes.toString());
            return;
        }
        processamentoService.processarEhSalvarDados(asset, dataReferencia, quotes, obj);
    }

}
