package br.com.cma.cmaimportador.service.request;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    private Integer id;
    private String name;
    private String sessionId;
    private String type;
    private Boolean sync;
    private Integer version;
    private String advUser;
    private String reason;
}
