package br.com.cma.cmaimportador.service;

import br.com.cma.cmaimportador.domain.ContractsEntity;
import br.com.cma.cmaimportador.repository.ContractsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ContractsServiceImpl implements ContractsService {

    private final ContractsRepository repo;

    public ContractsServiceImpl(ContractsRepository repo) {
        this.repo = repo;
    }


    public List<ContractsEntity> getContratos() {
        List<ContractsEntity> contracts = new ArrayList<>();
        repo.findAll().forEach(contracts::add);
        return contracts;

    }
}
