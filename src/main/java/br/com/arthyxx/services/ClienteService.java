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

    public List<ClienteResponseDTO> findAll(){
        List<Cliente> entities = repository.findAll();

        return mapper.toResponseDTOList(entities);
    }

    public ClienteResponseDTO findById(Long id){
        Cliente entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );

        return mapper.toResponseDTO(entity);
    }

    public ClienteResponseDTO create(CreateClienteDTO dto){
        Cliente entity = mapper.toEntity(dto);

        entity.setPassword(passwordEncoder.encode(dto.password()));

        return mapper.toResponseDTO(repository.save(entity));
    }

    public ClienteResponseDTO update(Long id, PutClienteDTO dto){
        Cliente entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );

        mapper.updateFromPutDTO(dto, entity);

        Cliente updatedEntity = repository.save(entity);

        return mapper.toResponseDTO(updatedEntity);
    }

    public ClienteResponseDTO partialUpdate(Long id, PatchClienteDTO dto){
        Cliente entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );

        mapper.updateFromPatchDTO(dto, entity);

        Cliente updatedEntity = repository.save(entity);

        return mapper.toResponseDTO(updatedEntity);
    }

    public void delete(Long id){
        Cliente entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado!")
        );

        repository.delete(entity);
    }
}
