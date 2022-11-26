package br.com.cma.cmaimportador.service.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ArrQuotes {
    private Integer index;
    private SymbolId symbolId;
    private Integer vendor;
    private String vendorDesc;
    private Boolean isValid;
    private String datetime;
    private List<ArrValues> arrValues;
    private String isin;
    private String precision;
    private String divisor;
    private String step;
    private String description;
    private List<Sessions> sessions;

}
