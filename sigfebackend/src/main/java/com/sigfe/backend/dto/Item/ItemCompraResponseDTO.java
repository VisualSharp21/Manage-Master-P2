package com.sigfe.backend.dto.Item;

import java.math.BigDecimal;

public record ItemCompraResponseDTO( // dados que saem
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
){
}
