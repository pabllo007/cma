package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.AssetsEntity;
import br.com.cma.cmaimportador.domain.AtivosEntity;

public interface ProcessamentoService {

    void executar(String sessionID, AtivosEntity asset, String timeRef);
    void salvar();

}
