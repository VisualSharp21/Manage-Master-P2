package com.sigfe.backend.dto.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemVendaResponseDTO(
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal

) {
}
