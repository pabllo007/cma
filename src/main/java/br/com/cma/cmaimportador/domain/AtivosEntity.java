package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ATIVOS", schema = "opcoes")
public class AtivosEntity {

    @Id
    @Column(name = "ID_ASSETS")
    private Integer id;

    @Column(name = "ASSET")
    private String asset;

    @Column(name = "TYPE")
    private String type;

//    @OneToMany(mappedBy = "ativo")
//    private List<SubAtivosEntity> subAtivos;

}
