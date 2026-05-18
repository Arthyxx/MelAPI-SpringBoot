package br.com.arthyxx.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PutClienteDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
        String name,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email,

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password
){
}
