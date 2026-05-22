package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.avaliacao.AvaliacaoProdutoResponseDTO;
import br.com.arthyxx.dto.avaliacao.CreateAvaliacaoProdutoDTO;
import br.com.arthyxx.dto.avaliacao.PatchAvaliacaoProdutoDTO;
import br.com.arthyxx.models.AvaliacaoProduto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvaliacaoProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AvaliacaoProduto toEntity(CreateAvaliacaoProdutoDTO dto);

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "produtoName", source = "produto.name")
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "clienteName", source = "cliente.name")
    AvaliacaoProdutoResponseDTO toResponseDTO(AvaliacaoProduto avaliacao);

    List<AvaliacaoProdutoResponseDTO> toResponseDTOList(List<AvaliacaoProduto> avaliacoes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromPatchDTO(PatchAvaliacaoProdutoDTO dto, @MappingTarget AvaliacaoProduto avaliacao);
}
