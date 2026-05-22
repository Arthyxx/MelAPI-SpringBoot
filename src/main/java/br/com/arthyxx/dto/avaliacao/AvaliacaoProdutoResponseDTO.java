package br.com.arthyxx.dto.avaliacao;

import java.time.LocalDateTime;

public record AvaliacaoProdutoResponseDTO(
        Long id,
        Integer rating,
        String comment,

        Long produtoId,
        String produtoName,

        Long clienteId,
        String clienteName,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
