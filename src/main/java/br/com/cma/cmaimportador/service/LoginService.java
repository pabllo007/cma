package br.com.cma.cmaimportador.service;

public interface LoginService {

    String executarLogin();

    void executarLogout(String sessionId);
}
