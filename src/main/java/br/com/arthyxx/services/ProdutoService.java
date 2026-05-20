package br.com.arthyxx.services;

import br.com.arthyxx.dto.produto.*;
import br.com.arthyxx.exceptions.BusinessException;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.ProdutoMapper;
import br.com.arthyxx.models.Categoria;
import br.com.arthyxx.models.Produto;
import br.com.arthyxx.repository.CategoriaRepository;
import br.com.arthyxx.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper mapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponseDTO> findAll(ProdutoFilterDTO filter, Pageable pageable){
        Page<Produto> produtos = produtoRepository.findAllWithFilter(
                filter.name(),
                filter.categoryId(),
                filter.active(),
                pageable
        );

        return produtos.map(produto -> mapper.toResponseDTO(produto));
    }

    public ProdutoResponseDTO findById(Long id){
        Produto entity = findProdutoById(id);

        return mapper.toResponseDTO(entity);
    }

    public ProdutoResponseDTO create(CreateProdutoDTO dto){
        if (produtoRepository.existsByName(dto.name())) throw new BusinessException("Já existe um produto com esse nome.");

        Categoria categoria = findCategoriaById(dto.categoryId());

        Produto entity = mapper.toEntity(dto);
        entity.setCategory(categoria);

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public ProdutoResponseDTO update(Long id, PutProdutoDTO dto){
        Produto entity = findProdutoById(id);
        Categoria categoria = findCategoriaById(dto.categoryId());

        mapper.updateFromPutDTO(dto, entity);
        entity.setCategory(categoria);

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public ProdutoResponseDTO partialUpdate(Long id, PatchProdutoDTO dto){
        Produto entity = findProdutoById(id);

        mapper.updateFromPatchDTO(dto, entity);

        if (dto.categoryId() != null){
            Categoria categoria = findCategoriaById(dto.categoryId());
            entity.setCategory(categoria);
        }

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public void delete(Long id){
        Produto entity = findProdutoById(id);

        produtoRepository.delete(entity);
    }

    private Produto findProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
    }

    private Categoria findCategoriaById(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );

        if (!categoria.isActive()){
            throw new BusinessException("Não é possível vincular produto a uma categoria inativa.");
        }

        return categoria;
    }
}
