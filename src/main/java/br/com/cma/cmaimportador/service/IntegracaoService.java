package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AtivosEntity;
import br.com.cma.cmaimportador.service.request.LoginRequest;
import br.com.cma.cmaimportador.service.request.LogoutRequest;
import br.com.cma.cmaimportador.service.request.SymbolSearchRequest;
import br.com.cma.cmaimportador.service.response.LoginResponse;
import br.com.cma.cmaimportador.service.response.SymbolSearchResponse;
import br.com.cma.cmaimportador.service.utils.DataUtils;
import br.com.cma.cmaimportador.service.utils.RequestBoby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegracaoService {
   private final WebClient webClient;

   @Autowired
   private AtivosService ativosService;

   @Autowired
   private ContratosFutrosService contratosFuturosService;

   @Autowired
   private AcoesService acoesService;


   public void executaIntegracao() {

      String timeRef = DataUtils.getHoraMinutoSegundo();
      String sessionId = getSessionId();

      List<AtivosEntity> listaAssets = ativosService.getAtivosType("AC");

      log.info("INICIO INTEGRAÇÃO CONTRATOS FUTUROS");
      listaAssets.forEach(x -> {

         //contratosFuturosService.executar(sessionId, x, timeRef);

         acoesService.executar(sessionId, x, timeRef);

      });
      logout(sessionId);
      log.info("FIM INTEGRAÇÃO CONTRATOS FUTUROS");

     // log.info("Monta request Ações");
     // SymbolSearchResponse acoes = getAcoesResponse(sessionId);
//
//      log.info("Monta request opções");
//      SymbolSearchResponse opcoes = getOpcoesResponse(sessionId);
//

   }

   //Login
   private String getSessionId(){
      log.info("Monta request do login");
      LoginRequest reqLogin = RequestBoby.montaBodyLogin();

      log.info("Realiza requisição do login");
      Mono<LoginResponse> loginResponseMono = webClient
              .post()
              .bodyValue(reqLogin)
              .accept(APPLICATION_JSON)
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
              .bodyToMono(LoginResponse.class);

      LoginResponse loginResponse = loginResponseMono.block();

      return loginResponse.getSessionId();
   }

   private void logout(String sessionId){
      log.info("Realiza logout");
      LogoutRequest reqLogin = RequestBoby.montaBodyLogout(sessionId);

      Mono<String> logoutResponseMono = webClient
              .post()
              .bodyValue(reqLogin)
              .accept(APPLICATION_JSON)
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
              .bodyToMono(String.class);

      String loginResponse = logoutResponseMono.block();
      log.info(loginResponse);

   }

   //Ações

   private SymbolSearchResponse getAcoesResponse(String sessionID) {
      SymbolSearchRequest symbolSearchRequest = RequestBoby.montaAcoesRequest(sessionID);
      Mono<SymbolSearchResponse> symbolResponse = webClient
              .post()
              .bodyValue(symbolSearchRequest)
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
              .bodyToMono(SymbolSearchResponse.class);
      SymbolSearchResponse symbolSearchResponse = symbolResponse.block();
      return symbolSearchResponse;
   }

   private SymbolSearchResponse getAcoesResponse(String sessionID, String asset, Integer pagina) {
      SymbolSearchRequest symbolSearchRequest = RequestBoby.montaAcoesRequest(sessionID, asset, pagina);
      Mono<SymbolSearchResponse> symbolResponse = webClient
              .post()
              .bodyValue(symbolSearchRequest)
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
              .bodyToMono(SymbolSearchResponse.class);
      SymbolSearchResponse symbolSearchResponse = symbolResponse.block();
      return symbolSearchResponse;
   }


   //Opçoes
   private SymbolSearchResponse getOpcoesResponse(String sessionID) {
      log.info("Sessão Opçoes ");
      SymbolSearchRequest symbolSearchRequest = RequestBoby.montaOpcoesRequest(sessionID);

      Mono<SymbolSearchResponse> symbolResponse = webClient
              .post()
              .bodyValue(symbolSearchRequest)
              .retrieve()
              .onStatus(HttpStatus::is4xxClientError,
                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
              .bodyToMono(SymbolSearchResponse.class);

      SymbolSearchResponse symbolSearchResponse = symbolResponse.block();
      return symbolSearchResponse;
   }
   //Contratos Futuros
//   private QuotesResponse getContratosFuturosResponse(String sessionID, String asset) {
//      QuotesRequest quotesRequest = RequestBoby.montaContratosFuturosRequest(sessionID, asset);
//      Mono<QuotesResponse> quotesResponse = webClient
//              .post()
//              .bodyValue(quotesRequest)
//              .accept(APPLICATION_JSON)
//              .retrieve()
//              .onStatus(HttpStatus::is4xxClientError,
//                      error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
//              .bodyToMono(QuotesResponse.class);
//
//      QuotesResponse quotes = quotesResponse.block();
//
//      return quotes;
//   }
}
