package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.categoria.CategoriaResumoResponseDTO;
import br.com.arthyxx.dto.produto.CreateProdutoDTO;
import br.com.arthyxx.dto.produto.PatchProdutoDTO;
import br.com.arthyxx.dto.produto.ProdutoResponseDTO;
import br.com.arthyxx.dto.produto.PutProdutoDTO;
import br.com.arthyxx.models.Categoria;
import br.com.arthyxx.models.Produto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    @Mapping(target = "category", ignore = true)
    Produto toEntity(CreateProdutoDTO dto);

    ProdutoResponseDTO toResponseDTO(Produto produto);

    List<ProdutoResponseDTO> toResponseDTOList(List<Produto> produtos);

    CategoriaResumoResponseDTO toCategoriaResumoResponseDTO(Categoria categoria);

    @Mapping(target = "category", ignore = true)
    void updateFromPutDTO(PutProdutoDTO dto, @MappingTarget Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    void updateFromPatchDTO(PatchProdutoDTO dto, @MappingTarget Produto produto);
}