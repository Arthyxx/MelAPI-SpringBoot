package br.com.arthyxx.dto.cliente;

import br.com.arthyxx.enums.UserRole;

public record ClienteResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String street,
        String addressNumber,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        UserRole role
) {
}
