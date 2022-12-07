package br.com.cma.cmaimportador.service.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginResponse {
    private String sessionId;
    private String name;
    private String sessionIdAlias;
    private Integer id;
    private String type;
    private Boolean sync;
    private Integer version;
    private String uid;
    private Boolean success;
    private Integer status;
}
