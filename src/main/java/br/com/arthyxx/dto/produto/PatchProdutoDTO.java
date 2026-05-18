package br.com.arthyxx.dto.produto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PatchProdutoDTO(

        @Size(min = 3, max = 120, message = "O nome deve ter entre 3 e 120 caracteres.")
        String name,

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
        String description,

        @Positive(message = "O preço deve ser maior que zero.")
        BigDecimal price,

        @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
        Integer stockQuantity,

        String imageUrl,

        Boolean active,

        Long categoryId

) {
}