package br.com.cma.cmaimportador.domain;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Asks", schema = "opcoes")
public class AsksEntity {

    @Id
    private Integer id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_ask")
    private Historics historics;
}
