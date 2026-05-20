package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.pedido.CreatePedidoDTO;
import br.com.arthyxx.dto.pedido.PedidoResponseDTO;
import br.com.arthyxx.dto.pedido.UpdateStatusPedidoDTO;
import br.com.arthyxx.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Endpoints de gerenciamento de pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar pedidos")
    @GetMapping
    public List<PedidoResponseDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public PedidoResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @Operation(summary = "Criar pedido")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO create(@RequestBody @Valid CreatePedidoDTO dto){
        return service.create(dto);
    }

    @Operation(summary = "Atualizar parcialmente o pedido por ID")
    @PatchMapping("/{id}/status")
    public PedidoResponseDTO updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateStatusPedidoDTO dto){
        return service.updateStatus(id, dto);
    }
}
