package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Assets", schema = "opcoes")
public class AssetsEntity {

    @Id
    private Integer id;

    @Column(name = "asset")
    private String asset;

    @Column(name = "type")
    private String type;

}
