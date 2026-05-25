package br.com.arthyxx.dto.produto;

import br.com.arthyxx.dto.categoria.CategoriaResumoResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String imageUrl,
        boolean active,


        CategoriaResumoResponseDTO category,

        Double averageRating,
        Long reviewsCount,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}