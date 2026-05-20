package br.com.arthyxx.repository;

import br.com.arthyxx.models.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    @Query(value = """
            SELECT * FROM produtos p
            WHERE (CAST(:name AS text) IS NULL OR p.name ILIKE CONCAT('%', CAST(:name AS text), '%'))
            AND (CAST(:categoryId AS bigint) IS NULL OR p.category_id = CAST(:categoryId AS bigint))
            AND (CAST(:active AS boolean) IS NULL OR p.active = CAST(:active AS boolean))
            """,
            countQuery = """
            SELECT COUNT(*) FROM produtos p
            WHERE (CAST(:name AS text) IS NULL OR p.name ILIKE CONCAT('%', CAST(:name AS text), '%'))
            AND (CAST(:categoryId AS bigint) IS NULL OR p.category_id = CAST(:categoryId AS bigint))
            AND (CAST(:active AS boolean) IS NULL OR p.active = CAST(:active AS boolean))
            """,
            nativeQuery = true)
    Page<Produto> findAllWithFilter(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("active") Boolean active,
            Pageable pageable
    );
}
