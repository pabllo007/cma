package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.domain.BidsAsksEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.mapper.MapStructMapper;
import br.com.cma.cmaimportador.service.request.RequestService;
import br.com.cma.cmaimportador.service.response.ArrQuotes;
import br.com.cma.cmaimportador.service.response.ArrValues;
import br.com.cma.cmaimportador.service.response.QuotesResponse;
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
public class ProcessamentoServiceImpl implements ProcessamentoService {

    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    private SerieHistoricaService serieHistoricaService;

    @Autowired
    private BidsAsksService bidsAsksService;

    @Autowired
    public ProcessamentoServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            SerieHistoricaService serieHistoricaService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.serieHistoricaService = serieHistoricaService;
    }

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

    @Override
    public void processarEhSalvarDados(AtivosEntity asset, Date dataReferencia, QuotesResponse quotes, SerieHistorica serieHistorica) {

        if (quotes.getArrQuotes() == null) {
            log.info("NÃO RETORNOU INFORMAÇÕES NO ArrQuotes");
            log.info(quotes.toString());
            return;
        }

        Optional<ArrQuotes> optArrQuotes = quotes.getArrQuotes().stream().findFirst();
        if (optArrQuotes.isPresent() && optArrQuotes.get().getArrValues() != null) {
            SerieHistorica obj = new SerieHistorica();
            obj.setHoraReferencia(serieHistorica.getHoraReferencia());
            obj.setTicker(serieHistorica.getTicker());
            obj.setBase(serieHistorica.getBase());
            obj.setMarket(serieHistorica.getMarket());
            obj.setAssetType(serieHistorica.getAssetType());
            obj.setExpiration(serieHistorica.getExpiration());
            obj.setDataReferencia(serieHistorica.getDataReferencia());
            obj.setHoraReferencia(serieHistorica.getHoraReferencia());
            if (serieHistorica.getStrike() != null) {
                obj.setStrike(serieHistorica.getStrike());
            }

            log.info("Incluindo informações de " + obj.getTicker());
            List<BidsAsksEntity> bidsAsksEntityList = new ArrayList<>();
            optArrQuotes.get().getArrValues().stream().forEach(x -> {
                setValuesSerieHistorica(obj, x);
                setValuesBidsAsks(x);
            });
            log.info("SALVANDO SERIE HISTORICA");
            log.info(obj.toString());
            serieHistoricaService.salvar(obj);
            montaBirsAsksEntityList(obj, bidsAsksEntityList, dataReferencia);
            log.info("SALVANDO BIDS e ASKS");
            log.info(bidsAsksEntityList.toString());
            bidsAsksService.salvarEmLote(bidsAsksEntityList);
        }
    }
    private void montaBirsAsksEntityList (SerieHistorica obj, List < BidsAsksEntity > bidsAsksEntityList, Date dataReferencia) {
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird).quantity(this.qtdBird).type("BID").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird2).quantity(this.qtdBird2).type("BID").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird3).quantity(this.qtdBird3).type("BID").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird4).quantity(this.qtdBird4).type("BID").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.bird5).quantity(this.qtdBird5).type("BID").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask).quantity(this.qtdAsk).type("ASK").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask2).quantity(this.qtdAsk2).type("ASK").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask3).quantity(this.qtdAsk3).type("ASK").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask4).quantity(this.qtdAsk4).type("ASK").dataReferencia(dataReferencia).serieHistoricas(obj).build());
        bidsAsksEntityList.add(BidsAsksEntity.builder().price(this.ask5).quantity(this.qtdAsk5).type("ASK").dataReferencia(dataReferencia).serieHistoricas(obj).build());
    }

    private void setValuesSerieHistorica(SerieHistorica obj, ArrValues x) {
        if (x.getQuantidadeAcumulada() != null && this.isInt(x.getQuantidadeAcumulada())) {
            obj.setQuantityTotal(Integer.parseInt(x.getQuantidadeAcumulada()));
        }
        if (x.getZeroUm() != null) {
            obj.setVariationLast(x.getZeroUm());
        }
        if (x.getExpiration() != null) {
            obj.setExpiration(x.getExpiration());
        }
        if (x.getDez() != null) {
            log.info("getDez()" + x.getDez());
            obj.setPriceLast(new BigDecimal(x.getDez()));
            obj.setSpot(new BigDecimal(x.getDez()));
        }
        if (x.getUmA() != null) {
            log.info("getUmA()" + x.getUmA());
            obj.setClosingLast(new BigDecimal(x.getUmA()));
        }
        if (x.getUmC() != null && this.isInt(x.getUmC())) {
            obj.setQuantityLast(Integer.parseInt(x.getUmC()));
        }
        if (x.getVinteQuatro() != null) {
            obj.setTimeLast(x.getVinteQuatro());
        }
    }

    public void setValuesBidsAsks (ArrValues x) {
        if (x.getAsk() != null) {
            log.info("getUmA()" + x.getAsk());
            this.ask = new BigDecimal(x.getAsk());
        }
        if (x.getAskDois() != null) {
            log.info("getAskDois()" + x.getAskDois());
            this.ask2 = new BigDecimal(x.getAskDois());
        }
        if (x.getAskTres() != null) {
            log.info("getAskTres()" + x.getAskTres());
            this.ask3 = new BigDecimal(x.getAskTres());
        }
        if (x.getAskQuatro() != null) {
            log.info("getAskQuatro()" + x.getAskQuatro());
            this.ask4 = new BigDecimal(x.getAskQuatro());
        }
        if (x.getAskCinco() != null) {
            log.info("getAskCinco()" + x.getAskCinco());
            this.ask5 = new BigDecimal(x.getAskCinco());
        }
        if (x.getBid() != null) {
            log.info("getBid()" + x.getBid());
            this.bird = new BigDecimal(x.getBid());
        }
        if (x.getBidDois() != null) {
            log.info("getBidDois()" + x.getBidDois());
            this.bird2 = new BigDecimal(x.getBidDois());
        }
        if (x.getBidTres() != null) {
            log.info("getBidTres()" + x.getBidTres());
            this.bird3 = new BigDecimal(x.getBidTres());
        }
        if (x.getBidQuatro() != null) {
            log.info("getBidQuatro()" + x.getBidQuatro());
            this.bird4 = new BigDecimal(x.getBidQuatro());
        }
        if (x.getBidCinco() != null) {
            log.info("getBidCinco()" + x.getBidCinco());
            this.bird5 = new BigDecimal(x.getBidCinco());
        }
        if (x.getQtdAsk() != null && isInt(x.getQtdAsk())) {
            this.qtdAsk = Integer.parseInt(x.getQtdAsk());
        }
        if (x.getQtdAsk2() != null && isInt(x.getQtdAsk2())) {
            this.qtdAsk2 = Integer.parseInt(x.getQtdAsk2());
        }
        if (x.getQtdAsk3() != null && isInt(x.getQtdAsk3())) {
            this.qtdAsk3 = Integer.parseInt(x.getQtdAsk3());
        }
        if (x.getQtdAsk4() != null && isInt(x.getQtdAsk4())) {
            this.qtdAsk4 = Integer.parseInt(x.getQtdAsk4());
        }
        if (x.getQtdAsk5() != null && isInt(x.getQtdAsk5())) {
            this.qtdAsk5 = Integer.parseInt(x.getQtdAsk5());
        }
        if (x.getQtdBird() != null && isInt(x.getQtdBird())) {
            this.qtdBird = Integer.parseInt(x.getQtdBird());
        }
        if (x.getQtdBird2() != null && isInt(x.getQtdBird2())) {
            this.qtdBird2 = Integer.parseInt(x.getQtdBird2());
        }
        if (x.getQtdBird3() != null && isInt(x.getQtdBird3())) {
            this.qtdBird3 = Integer.parseInt(x.getQtdBird3());
        }
        if (x.getQtdBird4() != null && isInt(x.getQtdBird4())) {
            this.qtdBird4 = Integer.parseInt(x.getQtdBird4());
        }
        if (x.getQtdBird5() != null && isInt(x.getQtdBird5())) {
            this.qtdBird5 = Integer.parseInt(x.getQtdBird5());
        }
    }

    public boolean isInt(String v) {
        try {
            Integer.parseInt(v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
