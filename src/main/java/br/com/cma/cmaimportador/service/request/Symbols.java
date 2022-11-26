package br.com.cma.cmaimportador.service.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Symbols {

    private String sourceId;
    private String symbol;

}
