package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.BidsAsksEntity;
import br.com.cma.cmaimportador.domain.SerieHistorica;
import br.com.cma.cmaimportador.repository.BidsAsksRepository;
import br.com.cma.cmaimportador.repository.SerieHistoricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BidsAsksServiceImpl implements BidsAsksService {

    @Autowired
    private BidsAsksRepository repo;

    @Override
    public BidsAsksEntity salvar(BidsAsksEntity obj) {
        obj = repo.save(obj);
        return obj;
    }

    @Override
    @Transactional
    public void salvarEmLote(List<BidsAsksEntity> listaBidAsks) {
        repo.saveAll(listaBidAsks);
    }
}
