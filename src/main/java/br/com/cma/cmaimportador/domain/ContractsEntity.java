package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CONTRACTS", schema = "opcoes")
public class ContractsEntity {

    @Id
    private Integer id;

    @Column(name = "contract")
    private String contract;
}
