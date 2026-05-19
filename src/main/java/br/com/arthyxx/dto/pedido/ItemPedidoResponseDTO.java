package br.com.arthyxx.dto.pedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long id,
        Long produtoId,
        String produtoName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
