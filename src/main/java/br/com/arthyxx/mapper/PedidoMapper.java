package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.pedido.ItemPedidoResponseDTO;
import br.com.arthyxx.dto.pedido.PedidoResponseDTO;
import br.com.arthyxx.models.ItemPedido;
import br.com.arthyxx.models.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.name", target = "clienteName")
    PedidoResponseDTO toResponseDTO(Pedido pedido);

    List<PedidoResponseDTO> toResponseDTOList(List<Pedido> pedidos);

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.name", target = "produtoName")
    ItemPedidoResponseDTO toItemResponseDTO(ItemPedido itemPedido);
}
