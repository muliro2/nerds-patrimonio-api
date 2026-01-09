package com.nerds.patrimonio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import com.nerds.patrimonio.api.enums.TipoEquipamento;

public record EquipamentoDTO(
    @NotBlank(message = "O nome é obrigatório") String nome,
    @NotNull(message = "O tipo é obrigatório") TipoEquipamento tipo,
    @NotBlank(message = "O número de tombamento é obrigatório") String numeroTombamento,
    @NotNull(message = "A data de adição é obrigatória") LocalDate dataAdicao
) {}