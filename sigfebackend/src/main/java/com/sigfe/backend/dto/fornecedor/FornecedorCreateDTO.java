package com.sigfe.backend.dto.fornecedor;

import jakarta.validation.constraints.NotBlank;

public record FornecedorCreateDTO (
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) { // dados que entram
}
