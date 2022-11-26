package br.com.cma.cmaimportador.service.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Oms {

    private String ip;
    private String channel;
    private String language;

}
