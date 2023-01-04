package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.request.RequestService;
import br.com.cma.cmaimportador.service.request.SymbolSearchRequest;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
import br.com.cma.cmaimportador.service.response.SymbolSearchResponse;
import br.com.cma.cmaimportador.service.utils.RequestBoby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpcoesServiceImpl implements OpcoesService {
    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    private SerieHistoricaService serieHistoricaService;

    @Autowired
    private AcoesService acoesService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private BidsAsksService bidsAsksService;

    private BigDecimal ask = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ask1 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ask2 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ask3 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ask4 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ask5 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal bird = new BigDecimal(BigInteger.ZERO);

    private BigDecimal bird1 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal bird2 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal bird3 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal bird4 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal bird5 = new BigDecimal(BigInteger.ZERO);

    private Integer qtdBird = 0;
    private Integer qtdBird2 = 0;
    private Integer qtdBird3 = 0;
    private Integer qtdBird4 = 0;
    private Integer qtdBird5 = 0;
    private Integer qtdAsk = 0;
    private Integer qtdAsk2 = 0;
    private Integer qtdAsk3 = 0;
    private Integer qtdAsk4 = 0;
    private Integer qtdAsk5 = 0;
    @Autowired
    public OpcoesServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            SerieHistoricaService serieHistoricaService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.serieHistoricaService = serieHistoricaService;
    }

    @Override
    public void executar(String sessionID, AtivosEntity asset, SerieHistorica serieHistorica) {
        Integer pagina = 0;
        SymbolSearchResponse obj = requestService.getSymbolSearchResponse(sessionID, asset.getAsset(), pagina);;
        if(serieHistorica == null) {
            new SerieHistorica();
        }
        obj.getSymbols().forEach(x -> {
            serieHistorica.setTicker(x.getSymbol());
            serieHistorica.setBase(x.getRoot());
            serieHistorica.setAssetType(getAsstType(x.getDescription()));
            serieHistorica.setStrike(new BigDecimal(x.getExercisePrice()));
            serieHistorica.setExpiration(x.getExerciseDate());
            serieHistorica.setMarket(x.getMarket());

            /// CHAMAR A SERVICE QUOTES
            asset.setAsset(x.getSymbol());
            acoesService.executar(sessionID, asset, serieHistorica, "12");

        });
    }

    private String getAsstType(String description) {
        if (description.toUpperCase().contains("CALL")) {
            return "Call";
        };

        if (description.toUpperCase().contains("PUT")) {
            return "Put";
        };
        return "";
    }
}
