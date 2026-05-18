package br.com.arthyxx.mapper;

import br.com.arthyxx.dto.categoria.CategoriaResponseDTO;
import br.com.arthyxx.dto.categoria.CreateCategoriaDTO;
import br.com.arthyxx.dto.categoria.PatchCategoriaDTO;
import br.com.arthyxx.dto.categoria.PutCategoriaDTO;
import br.com.arthyxx.models.Categoria;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toEntity(CreateCategoriaDTO dto);

    CategoriaResponseDTO toResponseDTO(Categoria categoria);

    List<CategoriaResponseDTO> toResponseDTOList(List<Categoria> categorias);

    void updateFromPutDTO(PutCategoriaDTO dto, @MappingTarget Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromPatchDTO(PatchCategoriaDTO dto, @MappingTarget Categoria categoria);
}