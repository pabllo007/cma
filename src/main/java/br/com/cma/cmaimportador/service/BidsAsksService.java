package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.BidsAsksEntity;

import java.util.List;

public interface BidsAsksService {
    BidsAsksEntity salvar(BidsAsksEntity obj);

    void salvarEmLote(List<BidsAsksEntity> listaBidAsks);
}
