package com.sigfe.backend.dto.fornecedor;

import com.sigfe.backend.model.Fornecedor;

public record FornecedorResponseDTO(

        Long id,
        String nome,
        String email,
        String telefone

) {
    // Construtor auxiliar (Entity → DTO)
    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEmail(),
                fornecedor.getTelefone()
        );
    }
} // dados que saem