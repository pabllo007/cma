package br.com.cma.cmaimportador.service;

public interface ProcessamentoService {

    void executar(String sessionID, String asset);
    void salvar();

}
