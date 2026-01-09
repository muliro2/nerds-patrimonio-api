package com.nerds.patrimonio.api.enums;

public enum TipoEquipamento {
    INFORMATICA("Informática"),
    MOBILIARIO("Mobiliário"),
    ELETRONICO("Eletrônico"),
    ELETRICO("Elétrico"),
    OUTRO("Outro");

    private final String descricao;

    TipoEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
