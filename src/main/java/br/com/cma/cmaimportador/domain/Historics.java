package br.com.cma.cmaimportador.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Historics", schema = "opcoes")
public class Historics {

    @Id
    private Long id;

    @Column(name = "date")
    private Date data;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "base")
    private String base;

    @Column(name = "strike")
    private BigDecimal strike;

    @Column(name = "expiration")
    private Date expiration;

    @Column(name = "market")
    private String market;

    @Column(name = "spot")
    private BigDecimal spot;

    @Column(name = "price_last")
    private BigDecimal priceLast;

    @Column(name = "quantity_last")
    private Long quantityLast;

    @Column(name = "time_last")
    private Date timeLast;

    @Column(name = "quantity_total")
    private Long quantityTotal;

    @OneToMany(mappedBy = "historics")
    private List<BidsEntity> birds;

    @OneToMany(mappedBy = "historics")
    private List<AsksEntity> asks;
}
