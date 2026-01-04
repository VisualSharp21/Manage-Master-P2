package com.sigfe.backend.dto.venda;

import com.sigfe.backend.dto.Item.ItemTransacaoDTO;

import java.util.List;

public record VendaCreateDTO ( // dados que entram
        List<ItemTransacaoDTO> itens
) {
}
