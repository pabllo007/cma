package br.com.cma.cmaimportador.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class SymbolSearchRequest implements Serializable {

    private static final long serialVersionUID = -1567189692013737534L;

    private Integer id;
    private String sessionId;
    private String type;
    private Boolean sync;
    private Integer timeoutHandler;
    private String failActionType;
    private Integer source;
    private Integer max;
    private String symbol;
    private String description;
    private String market;
    private Integer page;
    private String name;
    private Boolean match;

}