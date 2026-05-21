package br.com.arthyxx.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PutClienteDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
        String name,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,

        @NotBlank(message = "O telefone é obrigatório.")
        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
        String phone,

        @NotBlank(message = "A rua é obrigatória.")
        @Size(max = 120, message = "A rua deve ter no máximo 120 caracteres.")
        String street,

        @NotBlank(message = "O número é obrigatório.")
        @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
        String addressNumber,

        @NotBlank(message = "O bairro é obrigatório.")
        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
        String neighborhood,

        @NotBlank(message = "A cidade é obrigatória.")
        @Size(max = 80, message = "A cidade deve ter no máximo 80 caracteres.")
        String city,

        @NotBlank(message = "O estado é obrigatório.")
        @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres. Exemplo: CE.")
        String state,

        @NotBlank(message = "O CEP é obrigatório.")
        @Size(max = 10, message = "O CEP deve ter no máximo 10 caracteres.")
        String zipCode
) {
}