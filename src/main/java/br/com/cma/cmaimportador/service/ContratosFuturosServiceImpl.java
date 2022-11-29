package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;
import br.com.cma.cmaimportador.domain.BidsEntity;
import br.com.cma.cmaimportador.domain.Historics;
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
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContratosFuturosServiceImpl implements ContratosFutrosService {
    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    private HistoricsService historicsService;

    @Autowired
    public ContratosFuturosServiceImpl(
            WebClient webClient, MapStructMapper mapStructMapper,
            HistoricsService historicsService) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
        this.historicsService = historicsService;
    }

    @Override
    public void executar(String sessionID, AssetsEntity asset, String timeRef) {

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
            Historics historics = mapStructMapper.ArraQuotesToHistorics(optArrQuotes.get());
            historics.setTimeRef(timeRef);

            optArrQuotes.get().getArrValues().stream().forEach(x -> {
                if(x.getZeroUm() != null) {
                }
                if(x.getDez() != null) {
                    historics.setPriceLast(new BigDecimal(x.getDez()));
                }
                if(x.getUmA() != null) {
                    historics.setPriceLast(new BigDecimal(x.getUmA()));
                }
                if(x.getUmC() != null) {
                    historics.setQuantityLast(x.getUmC());
                }
                if(x.getVinteQuatro() != null) {
                    historics.setTimeLast(x.getVinteQuatro());
                }
            });

                //Salvar Sessions
                optArrQuotes.get().getSessions().forEach(x -> {
                });
                historicsService.salvar(historics);
        }

    }

    @Override
    public void salvar() {

    }
}
