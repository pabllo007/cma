package br.com.cma.cmaimportador.service.request;

import br.com.cma.cmaimportador.service.response.QuotesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RequiredArgsConstructor
@Service
public class RequestService {
    private final WebClient webClient;

    public QuotesResponse getQuotesRequest(QuotesRequest quotesRequest){
        Mono<QuotesResponse> quotesResponse = webClient
                .post()
                .bodyValue(quotesRequest)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
                .bodyToMono(QuotesResponse.class);

        QuotesResponse quotes = quotesResponse.block();
        return quotes;
    }
}
