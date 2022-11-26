package br.com.cma.cmaimportador.service.utils;

import br.com.cma.cmaimportador.service.request.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RequestBoby {

    @Value("${cma.str_host}")
    private String str_host;

    @Value("${cma.str_user}")
    private String str_user;

    @Value("${cma.str_passwd}")
    private String str_passwd;

    @Value("${cma.msg_id}")
    private String msg_id;

    @Value("${cma.tasks.fixed-delay}")
    private String teste20;

    public static LoginRequest montaBodyLogin() {
        return LoginRequest
                .builder()
                .id(1)
                .name("LoginRequest")
                .sessionId("")
                .type("s")
                .sync(true)
                .oms(Oms.builder().ip("0.0.0.0").channel("API").language("PT").build())
                .pass("Padrao01")
                .service("m")
                .transport("NoneAuto")
                .version(1)
                .user("STRFEEDCONSTANCIA01")
                .build();
    }


    public static SymbolSearchRequest montaAcoesRequest(String sessionId) {
        return SymbolSearchRequest
                .builder()
                .id(1)
                .name("SymbolSearchRequest")
                .sessionId(sessionId)
                .type("q")
                .sync(true)
                .timeoutHandler(120)
                .failActionType("closeconnection")
                .source(12)
                .max(1000)
                .symbol("ABEV")
                .description("")
                .market("0")
                .page(0)
                .match(false)
                .build();

    }

    public static SymbolSearchRequest montaOpcoesRequest(String sessionId) {
        return SymbolSearchRequest
                .builder()
                .id(1)
                .name("SymbolSearchRequest")
                .sessionId(sessionId)
                .type("q")
                .sync(true)
                .timeoutHandler(120)
                .failActionType("closeconnection")
                .source(57)
                .max(1000)
                .symbol("DI1*3")
                .description("")
                .market("")
                .page(0)
                .match(false)
                .build();

    }

    public static QuotesRequest montaContratosFuturosRequest(String sessionId, String asset) {
        QuotesRequest quotesRequest = new QuotesRequest();
        quotesRequest.setId(1);
        quotesRequest.setName("QuotesRequest");
        quotesRequest.setSessionId(sessionId);
        quotesRequest.setType("n");
        quotesRequest.setSync(true);
        quotesRequest.setTimeoutHandler(120);
        quotesRequest.setFailActionType("alert");
        quotesRequest.setFields("");
        quotesRequest.setRealtime(true);
        List<Symbols> symbols = new ArrayList<>();
        symbols.add(Symbols.builder().sourceId("12").symbol(asset.trim()).build());
        quotesRequest.setSymbols(symbols);
        quotesRequest.setSign(false);

        return quotesRequest;
    }

}
