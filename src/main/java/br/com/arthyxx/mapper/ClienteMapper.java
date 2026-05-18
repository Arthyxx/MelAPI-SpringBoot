package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.cliente.ClienteResponseDTO;
import br.com.arthyxx.dto.cliente.CreateClienteDTO;
import br.com.arthyxx.models.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(CreateClienteDTO dto);

    ClienteResponseDTO toResponseDTO(Cliente cliente);
}
