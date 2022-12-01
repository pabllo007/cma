package br.com.cma.cmaimportador;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeBirdAskEnum {
    BIRS("BID"),
    ASKS("ASK");

    private final String tipo;

}
