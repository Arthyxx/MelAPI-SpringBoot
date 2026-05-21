package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.cliente.ClienteResponseDTO;
import br.com.arthyxx.dto.cliente.CreateClienteDTO;
import br.com.arthyxx.dto.cliente.PatchClienteDTO;
import br.com.arthyxx.dto.cliente.PutClienteDTO;
import br.com.arthyxx.models.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(CreateClienteDTO dto);

    ClienteResponseDTO toResponseDTO(Cliente cliente);

    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    @Mapping(target = "password", ignore = true)
    void updateFromPutDTO(PutClienteDTO dto, @MappingTarget Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    void updateFromPatchDTO(PatchClienteDTO dto, @MappingTarget Cliente cliente);
}
