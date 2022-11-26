package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ASSETS_TYPE", schema = "opcoes")
public class AssetsTypeEntity {

    @Id
    private Integer id;

    @Column(name = "type")
    private String type;
}
