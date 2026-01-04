package com.sigfe.backend.dto.Item;

import java.math.BigDecimal;

public record ItemTransacaoCreateDTO(
        Long produtoId,
        Integer quantidade,
        BigDecimal preco
) {
}
