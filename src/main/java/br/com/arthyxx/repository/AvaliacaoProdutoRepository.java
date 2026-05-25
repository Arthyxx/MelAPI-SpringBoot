package br.com.arthyxx.repository;

import br.com.arthyxx.models.AvaliacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {

    List<AvaliacaoProduto> findByProdutoIdOrderByCreatedAtDesc(Long produtoId);

    Optional<AvaliacaoProduto> findByProdutoIdAndClienteId(Long produtoId, Long clienteId);

    boolean existsByProdutoIdAndClienteId(Long produtoId, Long clienteId);

    @Query("""
            SELECT COALESCE(AVG(a.rating), 0)
            FROM AvaliacaoProduto a
            WHERE a.produto.id = :produtoId
            """)
    Double findAvarageRatingByProdutoId(@Param("produtoId") Long produtoId);

    @Query("""
            SELECT COUNT(a)
            FROM AvaliacaoProduto a
            WHERE a.produto.id = :produtoId
            """)
    Long countByProdutoId(@Param("produtoId") Long produtoId);
}
