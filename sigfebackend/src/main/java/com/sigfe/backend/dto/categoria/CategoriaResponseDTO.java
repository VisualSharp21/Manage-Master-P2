package com.sigfe.backend.dto.categoria;

import com.sigfe.backend.model.Categoria;

public record CategoriaResponseDTO( // dados que saem
        Long id,
        String nome
) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
