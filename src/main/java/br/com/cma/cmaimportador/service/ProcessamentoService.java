package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;

public interface ProcessamentoService {

    void executar(String sessionID, AssetsEntity asset, String timeRef);
    void salvar();

}
