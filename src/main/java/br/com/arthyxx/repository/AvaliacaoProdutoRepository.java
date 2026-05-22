package br.com.arthyxx.repository;

import br.com.arthyxx.models.AvaliacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {

    List<AvaliacaoProduto> findByProdutoIdOrderByCreatedAtDesc(Long produtoId);

    Optional<AvaliacaoProduto> findByProdutoIdAndClienteId(Long produtoId, Long clienteId);

    boolean existsByProdutoIdAndClienteId(Long produtoId, Long clienteId);
}
