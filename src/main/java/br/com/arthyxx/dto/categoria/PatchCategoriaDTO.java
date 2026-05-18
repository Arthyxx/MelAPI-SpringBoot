package br.com.arthyxx.dto.categoria;

import jakarta.validation.constraints.Size;

public record PatchCategoriaDTO(

        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
        String description,

        Boolean active

) {
}