package br.com.arthyxx.dto.categoria;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
        Long id,
        String name,
        String description,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}