package br.com.cma.cmaimportador.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "BIDS_ASKS", schema = "opcoes")
public class BidsAsksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BIDS_ASKS")
    private Long id;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TYPE_BIDS_ASKS")
    private String type;

    @Column(name = "DATA_REFERENCIA")
    private Date dataReferencia;

    @ManyToOne
    @JoinColumn(name = "ID_HITORICS")
    private SerieHistorica serieHistoricas;
}
