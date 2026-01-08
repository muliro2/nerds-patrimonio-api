package com.nerds.patrimonio.api.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipamentoDTO(
    @NotBlank(message = "O nome é obrigatório") String nome,
    @NotBlank(message = "O tipo é obrigatório") String tipo,
    @NotBlank(message = "O número de série é obrigatório") String numeroSerie
) {}