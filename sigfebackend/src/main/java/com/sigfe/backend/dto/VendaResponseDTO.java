package com.sigfe.backend.dto;

import java.math.BigDecimal;

public record VendaResponseDTO(Long id,
                               String usuario,
                               String numeroDocumento,
                               String status,
                               BigDecimal valorTotal) {
}
