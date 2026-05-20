package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.produto.*;
import br.com.arthyxx.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ProdutoResponseDTO> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean active,
            Pageable pageable
    ){
        ProdutoFilterDTO filter = new ProdutoFilterDTO(name, categoryId, active);

        return service.findAll(filter, pageable);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO create(@RequestBody @Valid CreateProdutoDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO update(@PathVariable Long id, @RequestBody @Valid PutProdutoDTO dto){
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public ProdutoResponseDTO partialUpdate(@PathVariable Long id, @RequestBody @Valid PatchProdutoDTO dto){
        return service.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
