package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.repository.SerieHistoricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SerieHistoricaServiceImpl implements SerieHistoricaService {

    @Autowired
    private SerieHistoricaRepository repo;

    @Override
    @Transactional
    public SerieHistorica salvar(SerieHistorica obj) {
        obj = repo.save(obj);
        return obj;
    }
}
