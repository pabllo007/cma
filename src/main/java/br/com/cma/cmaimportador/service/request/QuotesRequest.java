package br.com.cma.cmaimportador.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class QuotesRequest implements Serializable {

    private static final long serialVersionUID = -1567189692013737534L;

    private Integer id;
    private String name;
    private String sessionId;
    private String type;
    private Boolean sync;
    private Integer timeoutHandler;
    private String failActionType;
    private String fields;
    private Boolean realtime;
    private List<Symbols> symbols;
    private Boolean sign;

    public QuotesRequest() {

    }
}