package com.proint.walletly.dto.conta;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ContaDTO(@NotBlank Long usuarioId,
                       @NotBlank Long instituicaoId,
                       @NotBlank String apelido,
                       @NotBlank String tipoConta,
                       BigDecimal saldoAtual) {
}
