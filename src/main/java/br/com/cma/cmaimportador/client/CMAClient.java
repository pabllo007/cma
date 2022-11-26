package br.com.cma.cmaimportador.client;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

//@Component
@Slf4j
public class CMAClient {

    @Value("${cma.str_user}")
    private String str_user;

    @Value("${cma.str_passwd}")
    private String str_passwd;

    private final WebClient webClient;

    public CMAClient(WebClient.Builder builder) {
        webClient = builder
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .resolver(DefaultAddressResolverGroup.INSTANCE)))
                .baseUrl("https://strfeedrt06.cma.com.br/execute")
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE,
                        "Content-Type", MediaType.APPLICATION_JSON_VALUE,
                        "Accept", "*/*",
                        "Accept-Encoding", "gzip, deflate, br",
                        "Connection", "keep-alive"
                )
                .build();
    }
}
