package br.com.arthyxx.services;

import br.com.arthyxx.dto.pedido.CreateItemPedidoDTO;
import br.com.arthyxx.dto.pedido.CreatePedidoDTO;
import br.com.arthyxx.dto.pedido.PedidoResponseDTO;
import br.com.arthyxx.dto.pedido.UpdateStatusPedidoDTO;
import br.com.arthyxx.enums.StatusPedido;
import br.com.arthyxx.exceptions.BusinessException;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.PedidoMapper;
import br.com.arthyxx.models.Cliente;
import br.com.arthyxx.models.ItemPedido;
import br.com.arthyxx.models.Pedido;
import br.com.arthyxx.models.Produto;
import br.com.arthyxx.repository.ClienteRepository;
import br.com.arthyxx.repository.PedidoRepository;
import br.com.arthyxx.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper mapper;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoMapper mapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findAll(){
        List<Pedido> entities = pedidoRepository.findAll();

        return mapper.toResponseDTOList(entities);
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO findById(Long id){
        Pedido entity = findPedidoById(id);

        return mapper.toResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findMyPedidos(String email){
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );

        List<Pedido> pedidos = pedidoRepository.findByClienteId(cliente.getId());

        return mapper.toResponseDTOList(pedidos);
    }

    @Transactional
    public PedidoResponseDTO create(CreatePedidoDTO dto){
        Cliente cliente = findClienteById(dto.clienteId());

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        BigDecimal totalPedido = BigDecimal.ZERO;

        for (CreateItemPedidoDTO itemDTO : dto.items()){
            Produto produto = findProdutoById(itemDTO.produtoId());

            if (!produto.isActive()){
                throw new BusinessException("Não é possível comprar um produto inativo.");
            }

            if (produto.getStockQuantity() < itemDTO.quantity()){
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getName());
            }

            BigDecimal unitPrice = produto.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemDTO.quantity()));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantity(itemDTO.quantity());
            item.setUnitPrice(unitPrice);
            item.setSubtotal(subtotal);

            pedido.addItem(item);

            produto.setStockQuantity(produto.getStockQuantity() - itemDTO.quantity());

            totalPedido = totalPedido.add(subtotal);
        }

        pedido.setTotalPrice(totalPedido);

        Pedido savedPedido = pedidoRepository.save(pedido);

        return mapper.toResponseDTO(savedPedido);
    }

    @Transactional
    public PedidoResponseDTO updateStatus(Long id, UpdateStatusPedidoDTO dto){
        Pedido entity = findPedidoById(id);

        validateStatusTransition(entity, dto);

        entity.setStatus(dto.status());

        Pedido updatedEntity = pedidoRepository.save(entity);

        return mapper.toResponseDTO(updatedEntity);
    }



    private Pedido findPedidoById(Long id){
        return pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado!")
        );
    }

    private Cliente findClienteById(Long id){
        return clienteRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    private Produto findProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
    }

    private void validateStatusTransition(Pedido pedido, UpdateStatusPedidoDTO dto){
        if (pedido.getStatus() == dto.status()){
            throw new BusinessException("O pedido já está com esse status.");
        }

        if (pedido.getStatus() == StatusPedido.CANCELADO){
            throw new BusinessException("Não é possível alterar o status de um pedido cancelado");
        }

        if (pedido.getStatus() == StatusPedido.ENTREGUE){
            throw new BusinessException("Não é possível alterar o status de um pedido entregue.");
        }
    }
}
