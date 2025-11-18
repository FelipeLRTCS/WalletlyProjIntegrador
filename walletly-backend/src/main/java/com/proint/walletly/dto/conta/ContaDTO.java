package com.proint.walletly.dto.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ContaDTO(
        Long id,
        @NotNull(message = "O usuário é obrigatório") Long usuarioId,
        @NotNull(message = "A instituição financeira é obrigatória") Long instituicaoId,
        @NotBlank(message = "O apelido é obrigatório") String apelido,
        @NotBlank(message = "O tipo de conta é obrigatório") String tipoConta,
        @NotNull(message = "O saldo atual é obrigatório") BigDecimal saldoAtual,
        OffsetDateTime dataUltimaSincronizacao
) {
}
