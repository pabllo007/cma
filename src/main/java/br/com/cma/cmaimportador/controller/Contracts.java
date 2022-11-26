package br.com.cma.cmaimportador.controller;

import br.com.cma.cmaimportador.domain.ContractsEntity;
import br.com.cma.cmaimportador.service.ContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class Contracts {

    @Autowired
    private ContractsService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContractsEntity>> buscarTodos() {
        return ResponseEntity.ok().body(service.getContratos());
    }

}
