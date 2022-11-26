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
public class SymbolSearchResponse {
    private String name;
    private String sessionId;
    private String sessionIdAlias;
    private Integer id;
    private String type;
    private Boolean sync;
    private Integer version;
    private String uid;
    private Boolean success;
    private Integer status;
    private String textual;
    private Integer total;
    private Integer page;
    private Integer max;
    private Integer totalCount;
    private List<Symbols> symbols;
}
