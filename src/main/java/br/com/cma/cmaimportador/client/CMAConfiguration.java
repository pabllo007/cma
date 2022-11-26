package br.com.cma.cmaimportador.client;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
@Configuration
public class CMAConfiguration {

    @Bean
    public WebClient createWebClient() {
        return WebClient
                .builder()
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
