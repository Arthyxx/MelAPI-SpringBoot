package br.com.arthyxx.dto.cliente;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClienteDTO (
    @NotBlank(message = "O nome é obrigatório!")
    String name,

    @NotBlank(message = "O email é obrigatório!")
    @Email(message = "Informe um email válido!")
    String email,

    @NotBlank(message = "A senha é obrigatório!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
    String password
){
}
