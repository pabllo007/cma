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

    @JsonProperty("10")
    private String dez;

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

}
