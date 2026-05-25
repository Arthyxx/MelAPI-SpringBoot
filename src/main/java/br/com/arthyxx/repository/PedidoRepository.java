package br.com.arthyxx.repository;

import br.com.arthyxx.enums.StatusPedido;
import br.com.arthyxx.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);

    Optional<Pedido> findByIdAndClienteId(Long pedidoId, Long clienteId);

    @Query("""
            SELECT COUNT(p) > 0
            FROM Pedido p
            JOIN p.items i
            WHERE p.cliente.id = :clienteId
            AND i.produto.id = :produtoId
            AND p.status = :status
            """)
    boolean existsPedidoEntregueComProduto(@Param("clienteId") Long clienteId,
                                           @Param("produtoId") Long produtoId,
                                           @Param("status")StatusPedido status);
}
