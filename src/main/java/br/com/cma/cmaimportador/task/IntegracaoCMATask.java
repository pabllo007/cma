package br.com.cma.cmaimportador.task;

import br.com.cma.cmaimportador.service.IntegracaoService;
import br.com.cma.cmaimportador.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class IntegracaoCMATask {

    @Autowired
    private IntegracaoService integracaoService;
    @Autowired
    private LoginService loginService;
    public static final String TIME_ZONE = "America/Sao_Paulo";
    public static final String EXP_SEGUNDA_SEXTA_10H_AS_18H = "1 * 10-18 * * MON-FRI";
    @Scheduled(cron = EXP_SEGUNDA_SEXTA_10H_AS_18H, zone=TIME_ZONE)
    public void execute() {
        log.info("INICIO EXECUÇÃO PROCESSAMENTO : " + LocalDateTime.now().toString());
        String sessionId = loginService.executarLogin();
        integracaoService.executaIntegracao(sessionId);
        loginService.executarLogout(sessionId);
        log.info("FIM EXECUÇÃO PROCESSAMENTO :" + LocalDateTime.now().toString());
    }
}
