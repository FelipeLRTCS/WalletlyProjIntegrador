package com.proint.walletly.dto.transacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoDTO(
        Long id,
        @NotNull(message = "A conta é obrigatória") Long contaId,
        Long categoriaId,
        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres")
        String descricao,
        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
        BigDecimal valor,
        @NotBlank(message = "O tipo de transação é obrigatório")
        @Size(max = 10, message = "O tipo de transação não pode ter mais de 10 caracteres")
        String tipoTransacao,
        @NotNull(message = "A data da transação é obrigatória")
        LocalDate dataTransacao
) {
}

