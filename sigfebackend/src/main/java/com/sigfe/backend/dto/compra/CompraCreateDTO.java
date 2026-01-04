package com.sigfe.backend.dto.compra;

import com.sigfe.backend.dto.Item.ItemTransacaoCreateDTO;
import com.sigfe.backend.dto.Item.ItemTransacaoDTO;

import java.util.List;

public record CompraCreateDTO ( // dados que entram
                                String usuario,
                                String formaPagamento,
                                Long fornecedorId,
                                List<ItemTransacaoCreateDTO> itens

) {
}
