package com.sigfe.backend.dto.compra;

import com.sigfe.backend.model.Compra;

import java.math.BigDecimal;

public record CompraResponseDTO(
        Long id,
        String usuario,
        String numeroDocumento,
        String status,
        BigDecimal valorTotal
) {
    public CompraResponseDTO(Compra compra) {
        this(
                compra.getId(),
                compra.getUsuario(),
                compra.getNumeroDocumento(),
                compra.getStatus().name(),
                compra.getValorTotal()
        );
    }
}
