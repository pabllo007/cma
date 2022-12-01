package br.com.cma.cmaimportador.service.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ArrValues {

    @JsonProperty("01")
    private String zeroUm;

    @JsonProperty("03")
    private String zeroTres;

    @JsonProperty("09")
    private String expiration;

    @JsonProperty("10")
    private String dez;

    @JsonProperty("19")
    private Integer quantidadeAcumulada;

    @JsonProperty("0A")
    private String zeroA;

    @JsonProperty("0B")
    private String zeroB;

    @JsonProperty("1A")
    private String umA;

    @JsonProperty("2A")
    private String doisA;

    @JsonProperty("2B")
    private String doisB;

    @JsonProperty("1C")
    private Integer umC;

    @JsonProperty("2C")
    private String doisC;

    @JsonProperty("7D")
    private String seteD;

    @JsonProperty("12")
    private String doze;

    @JsonProperty("23")
    private String vinteTres;

    @JsonProperty("24")
    private String vinteQuatro;

    @JsonProperty("27")
    private String vinteSete;

    @JsonProperty("28")
    private String vinteOito;

    @JsonProperty("29")
    private String vinteNove;

    @JsonProperty("65")
    private String sessentaCinco;

    @JsonProperty("14")
    private String bid;

    @JsonProperty("30")
    private String bidDois;

    @JsonProperty("31")
    private String bidTres;

    @JsonProperty("32")
    private String bidQuatro;

    @JsonProperty("33")
    private String bidCinco;

    @JsonProperty("15")
    private String ask;

    @JsonProperty("34")
    private String askDois;

    @JsonProperty("35")
    private String askTres;

    @JsonProperty("36")
    private String askQuatro;

    @JsonProperty("37")
    private String askCinco;

    @JsonProperty("06")
    private Integer qtdBird;

    @JsonProperty("07")
    private Integer qtdAsk;

    @JsonProperty("38")
    private Integer qtdBird2;

    @JsonProperty("39")
    private Integer qtdBird3;

    @JsonProperty("3A")
    private Integer qtdBird4;

    @JsonProperty("3B")
    private Integer qtdBird5;

    @JsonProperty("3C")
    private Integer qtdAsk2;

    @JsonProperty("3D")
    private Integer qtdAsk3;

    @JsonProperty("3E")
    private Integer qtdAsk4;

    @JsonProperty("3F")
    private Integer qtdAsk5;

}
