package br.com.arthyxx.services;

import br.com.arthyxx.dto.avaliacao.AvaliacaoProdutoResponseDTO;
import br.com.arthyxx.dto.avaliacao.CreateAvaliacaoProdutoDTO;
import br.com.arthyxx.dto.avaliacao.PatchAvaliacaoProdutoDTO;
import br.com.arthyxx.exceptions.BusinessException;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.AvaliacaoProdutoMapper;
import br.com.arthyxx.models.AvaliacaoProduto;
import br.com.arthyxx.models.Cliente;
import br.com.arthyxx.models.Produto;
import br.com.arthyxx.repository.AvaliacaoProdutoRepository;
import br.com.arthyxx.repository.ClienteRepository;
import br.com.arthyxx.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoProdutoService {

    private final AvaliacaoProdutoRepository avaliacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final AvaliacaoProdutoMapper mapper;

    public AvaliacaoProdutoService(AvaliacaoProdutoRepository avaliacaoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository, AvaliacaoProdutoMapper mapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoProdutoResponseDTO> findByProdutoId(Long produtoId){
        findProdutoById(produtoId);

        List<AvaliacaoProduto> avaliacoes = avaliacaoRepository.findByProdutoIdOrderByCreatedAtDesc(produtoId);

        return mapper.toResponseDTOList(avaliacoes);
    }

    @Transactional
    public AvaliacaoProdutoResponseDTO create(Long produtoId, String clienteEmail, CreateAvaliacaoProdutoDTO dto){
        Produto produto = findProdutoById(produtoId);
        Cliente cliente = findClienteByEmail(clienteEmail);

        if (avaliacaoRepository.existsByProdutoIdAndClienteId(produtoId, cliente.getId())){
            throw new BusinessException("Você já avaliou este produto. Edite sua avaliação existente.");
        }

        AvaliacaoProduto avaliacao = mapper.toEntity(dto);
        avaliacao.setProduto(produto);
        avaliacao.setCliente(cliente);

        AvaliacaoProduto savedAvaliacao = avaliacaoRepository.save(avaliacao);

        return mapper.toResponseDTO(savedAvaliacao);
    }

    @Transactional
    public AvaliacaoProdutoResponseDTO updateMinhaAvaliacao(Long produtoId, String clienteEmail, PatchAvaliacaoProdutoDTO dto){
        Cliente cliente = findClienteByEmail(clienteEmail);

        AvaliacaoProduto avaliacao = avaliacaoRepository.findByProdutoIdAndClienteId(produtoId, cliente.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Avaliação não encontrada!")
        );

        mapper.updateFromPatchDTO(dto, avaliacao);

        AvaliacaoProduto updatedAvaliacao = avaliacaoRepository.save(avaliacao);

        return mapper.toResponseDTO(updatedAvaliacao);
    }

    @Transactional
    public void deleteMinhaAvaliacao(Long produtoId, String clienteEmail){
        Cliente cliente = findClienteByEmail(clienteEmail);

        AvaliacaoProduto avaliacao = avaliacaoRepository.findByProdutoIdAndClienteId(produtoId, cliente.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Avaliação não encontrada!")
        );

        avaliacaoRepository.delete(avaliacao);
    }

    private Produto findProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado!")
        );
    }

    private Cliente findClienteByEmail(String email){
        return clienteRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }
}
