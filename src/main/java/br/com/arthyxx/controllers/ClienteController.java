package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.cliente.ClienteResponseDTO;
import br.com.arthyxx.dto.cliente.CreateClienteDTO;
import br.com.arthyxx.dto.cliente.PatchClienteDTO;
import br.com.arthyxx.dto.cliente.PutClienteDTO;
import br.com.arthyxx.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO create(@RequestBody @Valid CreateClienteDTO dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO update(@PathVariable Long id, @RequestBody @Valid PutClienteDTO dto){
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public ClienteResponseDTO partialUpdate(@PathVariable Long id, @RequestBody @Valid PatchClienteDTO dto){
        return service.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
