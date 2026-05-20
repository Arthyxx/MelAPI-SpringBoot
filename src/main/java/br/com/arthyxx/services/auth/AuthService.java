package br.com.arthyxx.services.auth;

import br.com.arthyxx.dto.auth.LoginRequestDTO;
import br.com.arthyxx.dto.auth.LoginResponseDTO;
import br.com.arthyxx.exceptions.BusinessException;
import br.com.arthyxx.models.Cliente;
import br.com.arthyxx.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto){
        Cliente cliente = clienteRepository.findByEmail(dto.email()).orElseThrow(
                () -> new BusinessException("Email ou senha inválidos.")
        );

        boolean passwordMatches = passwordEncoder.matches(dto.password(), cliente.getPassword());

        if (!passwordMatches) throw new BusinessException("Email ou senha inválidos.");

        String token = tokenService.generateToken(cliente);

        return new LoginResponseDTO(token);
    }
}
