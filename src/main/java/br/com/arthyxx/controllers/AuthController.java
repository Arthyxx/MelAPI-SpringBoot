package br.com.arthyxx.controllers;

import br.com.arthyxx.dto.auth.LoginRequestDTO;
import br.com.arthyxx.dto.auth.LoginResponseDTO;
import br.com.arthyxx.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto){
        return service.login(dto);
    }
}
