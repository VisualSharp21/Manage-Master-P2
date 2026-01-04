package com.sigfe.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String marca,
        BigDecimal preco,
        int quantidade,
        LocalDate validade,
        String nomeCategoria,
        boolean vencido
) {
    // Construtor auxiliar para facilitar a conversão da Entidade para DTO
    public ProdutoResponseDTO(com.sigfe.backend.model.Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getValidade(),
                produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem Categoria",
                produto.estaVencido()
        );
    }
}
