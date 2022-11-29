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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_REFE")
    private Date data;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "base")
    private String base;

    @Column(name = "asset_type")
    private String assetType;

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
    private Integer quantityLast;

    @Column(name = "time_last")
    private String timeLast;

    @Column(name = "quantity_total")
    private Integer quantityTotal;

    @Column(name = "timeRef")
    private String timeRef;

    @OneToMany(mappedBy = "historics")
    private List<BidsEntity> birds;

    @OneToMany(mappedBy = "historics")
    private List<AsksEntity> asks;
}
