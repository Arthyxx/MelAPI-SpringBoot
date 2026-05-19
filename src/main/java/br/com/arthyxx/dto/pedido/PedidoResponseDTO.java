package br.com.arthyxx.dto.pedido;

import br.com.arthyxx.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        Long clienteId,
        String clienteName,
        List<ItemPedidoResponseDTO> items,
        BigDecimal totalPrice,
        StatusPedido status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
