package com.proint.walletly.dto.transacao;

import java.math.BigDecimal;

public record TransacaoGroupedDTO(
        String groupKey,
        BigDecimal total,
        Long count
) {
}

