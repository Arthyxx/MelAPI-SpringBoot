package br.com.arthyxx.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProdutoDTO(

        @NotBlank(message = "O nome do produto é obrigatório.")
        @Size(min = 3, max = 120, message = "O nome deve ter entre 3 e 120 caracteres.")
        String name,

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
        String description,

        @NotNull(message = "O preço do produto é obrigatório.")
        @Positive(message = "O preço deve ser maior que zero.")
        BigDecimal price,

        @NotNull(message = "A quantidade em estoque é obrigatória.")
        @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
        Integer stockQuantity,

        String imageUrl,

        @NotNull(message = "A categoria do produto é obrigatória.")
        Long categoryId

) {
}