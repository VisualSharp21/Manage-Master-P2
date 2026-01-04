package com.sigfe.backend.dto;

import java.math.BigDecimal;

public record CompraResponseDTO(Long id,
                                String usuario,
                                String numeroDocumento,
                                String status,
                                BigDecimal valorTotal){
}
