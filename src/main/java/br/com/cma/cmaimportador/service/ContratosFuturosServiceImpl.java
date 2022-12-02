package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;
import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.BidsAsksEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.request.QuotesRequest;
import br.com.cma.cmaimportador.service.response.ArrQuotes;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContratosFuturosServiceImpl implements ContratosFutrosService {
    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    private SerieHistoricaService serieHistoricaService;

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
    public ContratosFuturosServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            SerieHistoricaService serieHistoricaService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.serieHistoricaService = serieHistoricaService;
    }

    @Override
    public void executar(String sessionID, AtivosEntity asset, String timeRef) {

        QuotesRequest quotesRequest = RequestBoby.montaContratosFuturosRequest(sessionID, asset.getAsset());
        Mono<QuotesResponse> quotesResponse = webClient
                .post()
                .bodyValue(quotesRequest)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
                .bodyToMono(QuotesResponse.class);

        QuotesResponse quotes = quotesResponse.block();

        Optional<ArrQuotes> optArrQuotes = quotes.getArrQuotes().stream().findFirst();
        if (optArrQuotes.isPresent() && optArrQuotes.get().getArrValues() != null) {
            SerieHistorica obj = mapStructMapper.ArraQuotesToSerieHistorica(optArrQuotes.get(), asset);
            obj.setHoraReferencia(timeRef);
            List<BidsAsksEntity> bidsAsksEntityList = new ArrayList<>();
            optArrQuotes.get().getArrValues().stream().forEach(x -> {
                if(x.getQuantidadeAcumulada() != null) {
                    obj.setQuantityTotal(x.getQuantidadeAcumulada());
                }
                if(x.getZeroUm() != null) {
                    obj.setVariationLast(x.getZeroUm());
                }
                if(x.getExpiration() != null) {
                    obj.setExpiration(x.getExpiration());
                }
                if(x.getDez() != null) {
                    obj.setPriceLast(new BigDecimal(x.getDez()));
                }
                if(x.getUmA() != null) {
                    obj.setClosingLast(new BigDecimal(x.getUmA()));
                }
                if(x.getUmC() != null) {
                    obj.setQuantityLast(x.getUmC());
                }
                if(x.getVinteQuatro() != null) {
                    obj.setTimeLast(x.getVinteQuatro());
                }
                if(x.getAsk() != null) {
                    this.ask = new BigDecimal(x.getAsk());
                }
                if(x.getAskDois() != null) {
                    this.ask2 = new BigDecimal(x.getAskDois());
                }
                if(x.getAskTres() != null) {
                    this.ask3 = new BigDecimal(x.getAskTres());
                }
                if(x.getAskQuatro() != null) {
                    this.ask4 = new BigDecimal(x.getAskQuatro());
                }
                if(x.getAskCinco() != null) {
                    this.ask5 = new BigDecimal(x.getAskCinco());
                }
                if(x.getBid() != null) {
                    this.bird = new BigDecimal(x.getBid());
                }
                if(x.getBidDois() != null) {
                    this.bird2 = new BigDecimal(x.getBidDois());
                }
                if(x.getBidTres() != null) {
                    this.bird3 = new BigDecimal(x.getBidTres());
                }
                if(x.getBidQuatro() != null) {
                    this.bird4 = new BigDecimal(x.getBidQuatro());
                }
                if(x.getBidCinco() != null) {
                    this.bird5 = new BigDecimal(x.getBidCinco());
                }
                if(x.getQtdAsk() != null) {
                    this.qtdAsk = x.getQtdAsk();
                }
                if(x.getQtdAsk2() != null) {
                    this.qtdAsk2 = x.getQtdAsk2();
                }
                if(x.getQtdAsk3() != null) {
                    this.qtdAsk3 = x.getQtdAsk3();
                }
                if(x.getQtdAsk4() != null) {
                    this.qtdAsk4 = x.getQtdAsk4();
                }
                if(x.getQtdAsk5() != null) {
                    this.qtdAsk5 = x.getQtdAsk5();
                }
                if(x.getQtdBird() != null) {
                    this.qtdBird = x.getQtdBird();
                }
                if(x.getQtdBird2() != null) {
                    this.qtdBird2 = x.getQtdBird2();
                }
                if(x.getQtdBird3() != null) {
                    this.qtdBird3 = x.getQtdBird3();
                }
                if(x.getQtdBird4() != null) {
                    this.qtdBird4 = x.getQtdBird4();
                }
                if(x.getQtdBird5() != null) {
                    this.qtdBird5 = x.getQtdBird5();
                }
            });

            serieHistoricaService.salvar(obj);

            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird).quantity(this.qtdBird).type("BID").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird2).quantity(this.qtdBird2).type("BID").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird3).quantity(this.qtdBird3).type("BID").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird4).quantity(this.qtdBird4).type("BID").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird5).quantity(this.qtdBird5).type("BID").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask).quantity(this.qtdAsk).type("ASK").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask2).quantity(this.qtdAsk2).type("ASK").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask3).quantity(this.qtdAsk3).type("ASK").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask4).quantity(this.qtdAsk4).type("ASK").serieHistoricas(obj).build());
            bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask5).quantity(this.qtdAsk5).type("ASK").serieHistoricas(obj).build());
            bidsAsksService.salvarEmLote(bidsAsksEntityList);
        }

    }

    @Override
    public void salvar() {

    }
}
