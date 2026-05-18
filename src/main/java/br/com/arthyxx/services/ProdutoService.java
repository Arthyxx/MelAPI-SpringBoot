package br.com.arthyxx.services;

import br.com.arthyxx.dto.produto.CreateProdutoDTO;
import br.com.arthyxx.dto.produto.PatchProdutoDTO;
import br.com.arthyxx.dto.produto.ProdutoResponseDTO;
import br.com.arthyxx.dto.produto.PutProdutoDTO;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.ProdutoMapper;
import br.com.arthyxx.models.Categoria;
import br.com.arthyxx.models.Produto;
import br.com.arthyxx.repository.CategoriaRepository;
import br.com.arthyxx.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

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

    public List<ProdutoResponseDTO> findAll(){
        List<Produto> entities = produtoRepository.findAll();

        return mapper.toResponseDTOList(entities);
    }

    public ProdutoResponseDTO findById(Long id){
        Produto entity = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );

        return mapper.toResponseDTO(entity);
    }

    public ProdutoResponseDTO create(CreateProdutoDTO dto){
        Categoria categoria = categoriaRepository.findById(dto.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
        );

        Produto entity = mapper.toEntity(dto);
        entity.setCategory(categoria);

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public ProdutoResponseDTO update(Long id, PutProdutoDTO dto){
        Produto entity = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );

        mapper.updateFromPutDTO(dto, entity);

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public ProdutoResponseDTO partialUpdate(Long id, PatchProdutoDTO dto){
        Produto entity = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );

        mapper.updateFromPatchDTO(dto, entity);

        return mapper.toResponseDTO(produtoRepository.save(entity));
    }

    public void delete(Long id){
        Produto entity = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );

        produtoRepository.delete(entity);
    }
}
