package br.com.arthyxx.services;

import br.com.arthyxx.dto.categoria.CategoriaResponseDTO;
import br.com.arthyxx.dto.categoria.CreateCategoriaDTO;
import br.com.arthyxx.dto.categoria.PatchCategoriaDTO;
import br.com.arthyxx.dto.categoria.PutCategoriaDTO;
import br.com.arthyxx.exceptions.BusinessException;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.CategoriaMapper;
import br.com.arthyxx.models.Categoria;
import br.com.arthyxx.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoriaResponseDTO> findAll(){
        List<Categoria> entities = repository.findAll();

        return mapper.toResponseDTOList(entities);
    }

    public CategoriaResponseDTO findById(Long id){
        Categoria entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrado!")
        );

        return mapper.toResponseDTO(entity);
    }

    public CategoriaResponseDTO create(CreateCategoriaDTO dto){
        if (repository.existsByName(dto.name())) throw new BusinessException("Já existe uma categoria com esse nome.");

        Categoria entity = mapper.toEntity(dto);

        return mapper.toResponseDTO(repository.save(entity));
    }

    public CategoriaResponseDTO update(Long id, PutCategoriaDTO dto){
        Categoria entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrado!")
        );

        if (repository.existsByNameAndIdNot(dto.name(), id)) throw new BusinessException("Já existe outra categoria com esse nome.");

        mapper.updateFromPutDTO(dto, entity);

        return mapper.toResponseDTO(repository.save(entity));
    }

    public CategoriaResponseDTO partialUpdate(Long id, PatchCategoriaDTO dto){
        Categoria entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrado!")
        );

        if (dto.name() != null && repository.existsByNameAndIdNot(dto.name(), id)){
            throw new BusinessException("Já existe outra categoria com esse nome.");
        }

        mapper.updateFromPatchDTO(dto, entity);

        return mapper.toResponseDTO(repository.save(entity));
    }

    public void delete(Long id){
        Categoria entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrado!")
        );

        repository.delete(entity);
    }
}
