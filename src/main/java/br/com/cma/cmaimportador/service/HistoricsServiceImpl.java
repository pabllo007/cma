package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.Historics;
import br.com.cma.cmaimportador.repository.HistoricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricsServiceImpl implements HistoricsService {

    @Autowired
    private HistoricsRepository repo;

    @Override
    public Historics salvar(Historics historics) {
        historics = repo.save(historics);
        return historics;
    }
}
