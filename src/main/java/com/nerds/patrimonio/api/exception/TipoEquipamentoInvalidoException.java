package com.nerds.patrimonio.api.exception;

public class TipoEquipamentoInvalidoException extends RegraNegocioException {
    public TipoEquipamentoInvalidoException(String message) {
        super(message);
    }

    public TipoEquipamentoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
