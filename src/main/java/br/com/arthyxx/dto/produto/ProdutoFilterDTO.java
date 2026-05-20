package br.com.arthyxx.dto.produto;

public record ProdutoFilterDTO(
        String name,
        Long categoryId,
        Boolean active
) {
}
