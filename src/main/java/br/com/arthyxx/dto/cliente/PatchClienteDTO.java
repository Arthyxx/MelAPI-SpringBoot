package br.com.arthyxx.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record PatchClienteDTO(
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
        String name,

        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,

        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
        String phone,

        @Size(max = 120, message = "A rua deve ter no máximo 120 caracteres.")
        String street,

        @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
        String addressNumber,

        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
        String neighborhood,

        @Size(max = 80, message = "A cidade deve ter no máximo 80 caracteres.")
        String city,

        @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres. Exemplo: CE.")
        String state,

        @Size(max = 10, message = "O CEP deve ter no máximo 10 caracteres.")
        String zipCode
) {
}