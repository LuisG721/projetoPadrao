package com.projeto_padrao.models.chamados;

import org.jetbrains.annotations.NotNull;

public enum StatusImpressora {
    FUNCIONANDO("Funcionando"),
    QUEBRADA("Quebrada"),
    REPARO("Em reparo");

    private final String stringValue;
    private StatusImpressora(String toString) {
        stringValue = toString;
    }

    @NotNull
    @Override
    public String toString() {
        return stringValue;
    }
}
