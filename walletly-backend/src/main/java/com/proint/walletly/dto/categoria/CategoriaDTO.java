package com.proint.walletly.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres")
        String nome,
        @NotBlank(message = "A URL da imagem da categoria é obrigatória")
        String urlImagemCategoria
) {
}

