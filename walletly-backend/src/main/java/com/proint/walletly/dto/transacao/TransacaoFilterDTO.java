package com.proint.walletly.dto.transacao;

import java.time.LocalDate;

public record TransacaoFilterDTO(
        Long contaId,
        Long categoriaId,
        String tipoTransacao,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}

