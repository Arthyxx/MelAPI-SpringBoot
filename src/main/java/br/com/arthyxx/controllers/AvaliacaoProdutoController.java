package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.avaliacao.AvaliacaoProdutoResponseDTO;
import br.com.arthyxx.dto.avaliacao.CanReviewProdutoDTO;
import br.com.arthyxx.dto.avaliacao.CreateAvaliacaoProdutoDTO;
import br.com.arthyxx.dto.avaliacao.PatchAvaliacaoProdutoDTO;
import br.com.arthyxx.services.AvaliacaoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Avaliações de Produto", description = "Endpoints de avaliações dos produtos")
@RestController
@RequestMapping("/api/produtos/{produtoId}/avaliacoes")
public class AvaliacaoProdutoController {

    private final AvaliacaoProdutoService service;

    public AvaliacaoProdutoController(AvaliacaoProdutoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar avaliações de um produto")
    @GetMapping
    public List<AvaliacaoProdutoResponseDTO> findByProdutoId(@PathVariable Long produtoId){
        return service.findByProdutoId(produtoId);
    }

    @Operation(summary = "Verificar se o cliente logado pode avaliar o produto")
    @GetMapping("/pode-avaliar")
    public CanReviewProdutoDTO canReview(@PathVariable Long produtoId, Authentication authentication){
        return service.canReview(produtoId, authentication.getName());
    }

    @Operation(summary = "Criar avaliação para um produto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoProdutoResponseDTO create(@PathVariable Long produtoId, @RequestBody @Valid CreateAvaliacaoProdutoDTO dto, Authentication authentication){
        return service.create(produtoId, authentication.getName(), dto);
    }

    @Operation(summary = "Editar minha avaliação de um produto")
    @PatchMapping("/minha")
    public AvaliacaoProdutoResponseDTO updateMinhaAvaliacao(@PathVariable Long produtoId, @RequestBody @Valid PatchAvaliacaoProdutoDTO dto, Authentication authentication){
        return service.updateMinhaAvaliacao(produtoId, authentication.getName(), dto);
    }

    @Operation(summary = "Deletar minha avaliação")
    @DeleteMapping("/minha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMinhaAvaliacao(@PathVariable Long produtoId, Authentication authentication){
        service.deleteMinhaAvaliacao(produtoId, authentication.getName());
    }
}
