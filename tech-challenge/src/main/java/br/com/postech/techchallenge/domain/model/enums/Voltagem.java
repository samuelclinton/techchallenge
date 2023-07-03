package br.com.postech.techchallenge.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Voltagem {

    V_110(110),
    V_220(220);

    @Getter
    private final Integer volts;
}
