package com.sigfe.backend.dto.venda;

import com.sigfe.backend.dto.Item.ItemVendaResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public record VendaResponseDTO(Long id, // dados que saem
                               String usuario,
                               String numeroDocumento,
                               String status,
                               BigDecimal valorTotal,
                               List<ItemVendaResponseDTO> itens) {
}
