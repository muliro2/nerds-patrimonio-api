package com.nerds.patrimonio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipamentoDTO(
    @NotBlank(message = "O nome é obrigatório") String nome,
    @NotBlank(message = "O tipo é obrigatório") String tipo,
    @NotBlank(message = "O número de tombamento é obrigatório") String numeroTombamento,
    @NotNull(message = "A data de adição é obrigatória") LocalDate dataAdicao
) {}