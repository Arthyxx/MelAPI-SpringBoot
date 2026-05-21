package br.com.arthyxx.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClienteDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
        String name,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Email inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, max = 30, message = "A senha deve ter entre 6 e 30 caracteres.")
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

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
        String complement,

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