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



    @Scheduled(fixedDelayString = "${cma.tasks.fixed-delay}")
    public void execute() {
        integracaoService.executaIntegracao();
    }
}
