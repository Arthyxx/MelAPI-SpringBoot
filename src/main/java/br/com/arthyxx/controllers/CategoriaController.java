package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.categoria.CategoriaResponseDTO;
import br.com.arthyxx.dto.categoria.CreateCategoriaDTO;
import br.com.arthyxx.dto.categoria.PatchCategoriaDTO;
import br.com.arthyxx.dto.categoria.PutCategoriaDTO;
import br.com.arthyxx.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO create(@RequestBody @Valid CreateCategoriaDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO update(@PathVariable Long id, @RequestBody @Valid PutCategoriaDTO dto){
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public CategoriaResponseDTO partialUpdate(@PathVariable Long id, @RequestBody @Valid PatchCategoriaDTO dto){
        return service.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
