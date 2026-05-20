package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.categoria.CategoriaResponseDTO;
import br.com.arthyxx.dto.categoria.CreateCategoriaDTO;
import br.com.arthyxx.dto.categoria.PatchCategoriaDTO;
import br.com.arthyxx.dto.categoria.PutCategoriaDTO;
import br.com.arthyxx.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categorias", description = "Endpoints de gerenciamento de categorias")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar categorias")
    @GetMapping
    public List<CategoriaResponseDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Buscar categoria por ID")
    @GetMapping("/{id}")
    public CategoriaResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Criar categoria")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO create(@RequestBody @Valid CreateCategoriaDTO dto){
        return service.create(dto);
    }

    @Operation(summary = "Atualizar categoria por ID")
    @PutMapping("/{id}")
    public CategoriaResponseDTO update(@PathVariable Long id, @RequestBody @Valid PutCategoriaDTO dto){
        return service.update(id, dto);
    }

    @Operation(summary = "Atualizar parcialmente a categoria por ID")
    @PatchMapping("/{id}")
    public CategoriaResponseDTO partialUpdate(@PathVariable Long id, @RequestBody @Valid PatchCategoriaDTO dto){
        return service.partialUpdate(id, dto);
    }

    @Operation(summary = "Deletar categoria por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
