package br.com.cma.cmaimportador.task;

import br.com.cma.cmaimportador.service.IntegracaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IntegracaoCMATask {

    @Autowired
    private IntegracaoService integracaoService;

    public static final String TIME_ZONE = "America/Sao_Paulo";
    public static final String EXP_SEGUNDA_SEXTA_10H_AS_18H = "* * 10-18 * * MON-FRI";
    @Scheduled(cron = EXP_SEGUNDA_SEXTA_10H_AS_18H, zone=TIME_ZONE)
    public void execute() {
        integracaoService.executaIntegracao();
    }
}
