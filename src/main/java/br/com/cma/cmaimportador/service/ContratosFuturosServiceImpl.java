package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.BidsEntity;
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

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContratosFuturosServiceImpl implements ContratosFutrosService {
    private final WebClient webClient;

    private MapStructMapper mapStructMapper;

    @Autowired
    public ContratosFuturosServiceImpl(WebClient webClient, MapStructMapper mapStructMapper) {
        this.webClient = webClient;
        this.mapStructMapper = mapStructMapper;
    }

    @Override
    public void executar(String sessionID, String asset) {

        QuotesRequest quotesRequest = RequestBoby.montaContratosFuturosRequest(sessionID, asset);
        Mono<QuotesResponse> quotesResponse = webClient
                .post()
                .bodyValue(quotesRequest)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
                .bodyToMono(QuotesResponse.class);

        QuotesResponse quotes = quotesResponse.block();

        BidsEntity bidsEntity = new BidsEntity();

        Optional<ArrQuotes> optArrQuotes = quotes.getArrQuotes().stream().findFirst();
        if (optArrQuotes.isPresent() && optArrQuotes.get().getArrValues() != null) {
            log.info("CONTRATO: " + optArrQuotes.get().getSymbolId().getSymbol());

                //Salvar ArrQuotes
                optArrQuotes.get().getArrValues().stream().forEach(x -> {
                    if(optArrQuotes.get().getSymbolId().getSymbol().equals("AZUL4")) {

                            if(x.getZeroUm() != null) {
                                log.info("01: " + x.getZeroUm());
                            }
                            if(x.getDez() != null) {
                                log.info("10: " + x.getDez());
                            }
                            if(x.getUmA() != null) {
                                log.info("1A: " + x.getUmA());
                            }
                            if(x.getUmC() != null) {
                                log.info("1C: " + x.getUmC());
                            }
                            if(x.getVinteQuatro() != null) {
                                log.info("24: " + x.getVinteQuatro());
                            }

                    }
                });

                //Salvar Sessions
                optArrQuotes.get().getSessions().forEach(x -> {
                    log.info("Begin: " + x.getBegin());
                    log.info("END: " + x.getEnd());
                });
                log.info("=======================================================================");

        }

    }

    @Override
    public void salvar() {

    }
}
