package br.com.arthyxx.dto.cliente;

import br.com.arthyxx.enums.UserRole;

public record ClienteResponseDTO(
        Long id,
        String name,
        String email,
        UserRole role
) {
}
