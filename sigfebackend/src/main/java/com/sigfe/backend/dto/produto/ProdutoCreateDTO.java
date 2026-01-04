package com.sigfe.backend.dto.produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoCreateDTO ( // dados que entram
        String nome,
        String marca,
        BigDecimal preco,
        Integer quantidade,
        LocalDate validade,
        Long categoriaId
) {
}
