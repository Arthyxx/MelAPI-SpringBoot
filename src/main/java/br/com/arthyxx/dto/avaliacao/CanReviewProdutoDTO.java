package br.com.arthyxx.dto.avaliacao;

public record CanReviewProdutoDTO(
        boolean canReview,
        String message
) {
}
