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
@Table(name = "SERIE_HISTORICA", schema = "opcoes")
public class SerieHistorica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HITORICS")
    private Long id;

    @Column(name = "DATA_REFERENCIA")
    private Date dataReferencia;

    @Column(name = "TIME_REFERENCIA")
    private String horaReferencia;

    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "BASE")
    private String base;

    @Column(name = "ASSET_TYPE")
    private String assetType;

    @Column(name = "STRIKE")
    private BigDecimal strike;

    @Column(name = "EXPIRATION")
    private String expiration;

    @Column(name = "MARKET")
    private String market;

    @Column(name = "SPOT")
    private BigDecimal spot;

    @Column(name = "VARIATION_LAST")
    private String variationLast;

    @Column(name = "PRICE_LAST")
    private BigDecimal priceLast;

    @Column(name = "CLOSING_LAST")
    private BigDecimal closingLast;

    @Column(name = "QUANTITY_LAST")
    private Integer quantityLast;

    @Column(name = "TIME_LAST")
    private String timeLast;

    @Column(name = "QUANTITY_TOTAL")
    private Integer quantityTotal;

    @ManyToOne
    @JoinColumn(name = "ID_ASSETS")
    private AtivosEntity ativos;

    @OneToMany(mappedBy = "serieHistoricas")
    private List<BidsAsksEntity> asks;
}
