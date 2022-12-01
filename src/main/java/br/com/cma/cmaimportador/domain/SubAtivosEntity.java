package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUB_ATIVOS", schema = "opcoes")
public class SubAtivosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUBTYPE")
    private Integer id;

    @Column(name = "SUBTYPE")
    private String subType;

    @ManyToOne
    @JoinColumn(name = "ID_ASSETS")
    private AtivosEntity ativo;


}
