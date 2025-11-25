package com.proint.walletly.dto.instituicao;

import jakarta.validation.constraints.NotBlank;

public record InstituicaoDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "A URL do logo é obrigatória") String logoUrl
) {
}
