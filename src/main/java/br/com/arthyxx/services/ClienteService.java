package br.com.arthyxx.services;

import br.com.arthyxx.dto.cliente.ClienteResponseDTO;
import br.com.arthyxx.dto.cliente.CreateClienteDTO;
import br.com.arthyxx.dto.cliente.PatchClienteDTO;
import br.com.arthyxx.dto.cliente.PutClienteDTO;
import br.com.arthyxx.exceptions.ResourceNotFoundException;
import br.com.arthyxx.mapper.ClienteMapper;
import br.com.arthyxx.models.Cliente;
import br.com.arthyxx.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        List<Cliente> entities = repository.findAll();

        return mapper.toResponseDTOList(entities);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Cliente entity = findClienteById(id);

        return mapper.toResponseDTO(entity);
    }

    @Transactional
    public ClienteResponseDTO create(CreateClienteDTO dto) {
        Cliente entity = mapper.toEntity(dto);

        entity.setPassword(passwordEncoder.encode(dto.password()));

        return mapper.toResponseDTO(repository.save(entity));
    }

    @Transactional
    public ClienteResponseDTO update(Long id, PutClienteDTO dto) {
        Cliente entity = findClienteById(id);

        mapper.updateFromPutDTO(dto, entity);

        if (hasText(dto.password())) {
            entity.setPassword(passwordEncoder.encode(dto.password()));
        }

        Cliente updatedEntity = repository.save(entity);

        return mapper.toResponseDTO(updatedEntity);
    }

    @Transactional
    public ClienteResponseDTO partialUpdate(Long id, PatchClienteDTO dto) {
        Cliente entity = findClienteById(id);

        mapper.updateFromPatchDTO(dto, entity);

        if (hasText(dto.password())) {
            entity.setPassword(passwordEncoder.encode(dto.password()));
        }

        Cliente updatedEntity = repository.save(entity);

        return mapper.toResponseDTO(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        Cliente entity = findClienteById(id);

        repository.delete(entity);
    }

    private Cliente findClienteById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}