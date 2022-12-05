package br.com.cma.cmaimportador.service;


import br.com.cma.cmaimportador.service.request.LoginRequest;
import br.com.cma.cmaimportador.service.request.LogoutRequest;
import br.com.cma.cmaimportador.service.response.LoginResponse;
import br.com.cma.cmaimportador.service.utils.RequestBoby;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginImplService implements LoginService {

    private final WebClient webClient;

    @Override
    public String executarLogin() {
        log.info("MONTA REQUEST LOGIN");
        LoginRequest reqLogin = RequestBoby.montaBodyLogin();

        log.info("REALIZA REQUISIÇÃO LOGIN");
        Mono<LoginResponse> loginResponseMono = webClient
                .post()
                .bodyValue(reqLogin)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
                .bodyToMono(LoginResponse.class);

        LoginResponse loginResponse = loginResponseMono.block();
        log.info("FINALIZA REQUISIÇÃO LOGIN");
        return loginResponse.getSessionId();
    }

    @Override
    public void executarLogout(String sessionId) {
        log.info("REALIZA REQUISIÇÃO LOGOUT");
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
        log.info("FINALIZA REQUISIÇÃO LOGOUT");
    }
}
