package br.com.cma.cmaimportador.service.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Symbols {
    private Integer source;
    private String symbol;
    private String description;
    private String market;
    private String[] indexes;
    private Boolean enabled;
    private String currency;
    private String ISIN;
    private String step;
    private String precision;
    private String root;
    private String timezone;
    private String time_open;
    private String time_close;
    private String after_open;
    private String after_close;
    private String conversionFactor;
    private String multiplicator;
    private String adjust;
    private String lot;
    private String closePrice;
    private String closeDate;
    private String exercisePrice;
    private String exerciseDate;
    private String alterDate;
    private String now_exc;
}
