package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.pedido.CreatePedidoDTO;
import br.com.arthyxx.dto.pedido.PedidoResponseDTO;
import br.com.arthyxx.dto.pedido.UpdateStatusPedidoDTO;
import br.com.arthyxx.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO create(@RequestBody @Valid CreatePedidoDTO dto){
        return service.create(dto);
    }

    @PatchMapping("/{id}/status")
    public PedidoResponseDTO updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateStatusPedidoDTO dto){
        return service.updateStatus(id, dto);
    }
}
