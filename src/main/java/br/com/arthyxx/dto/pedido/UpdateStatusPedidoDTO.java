package br.com.arthyxx.dto.pedido;

import br.com.arthyxx.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusPedidoDTO(
        @NotNull(message = "O status do pedido é obrigatório.")
        StatusPedido status
) {
}
