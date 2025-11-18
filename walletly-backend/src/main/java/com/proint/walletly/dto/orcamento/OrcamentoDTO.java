package com.proint.walletly.dto.orcamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrcamentoDTO(
        Long id,
        @NotNull(message = "O usuário é obrigatório") Long usuarioId,
        @NotNull(message = "A categoria é obrigatória") Long categoriaId,
        @NotNull(message = "O valor máximo é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
        BigDecimal valorMaximo,
        @NotNull(message = "O mês é obrigatório") Integer mes,
        @NotNull(message = "O ano é obrigatório") Integer ano
) {
}

