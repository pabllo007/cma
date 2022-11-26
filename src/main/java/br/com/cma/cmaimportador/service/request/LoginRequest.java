package br.com.cma.cmaimportador.service.request;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private Integer id;
    private String name;
    private String sessionId;
    private String type;
    private Boolean sync;
    private Oms oms;
    private String pass;
    private String service;
    private String transport;
    private Integer version;
    private String user;
}
