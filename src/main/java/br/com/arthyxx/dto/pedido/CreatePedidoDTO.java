package br.com.arthyxx.dto.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreatePedidoDTO(
        @NotNull(message = "O cliente é obrigatório.")
        Long clienteId,

        @NotEmpty(message = "O pedido precisa ter pelo menos um item.")
        List<@Valid CreateItemPedidoDTO> items
) {
}
