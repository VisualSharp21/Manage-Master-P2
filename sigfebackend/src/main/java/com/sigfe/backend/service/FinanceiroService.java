package com.sigfe.backend.service;

import com.sigfe.backend.model.*;
import com.sigfe.backend.model.enums.OrigemMovimentacao;
import com.sigfe.backend.model.enums.StatusVenda;
import com.sigfe.backend.model.enums.TipoMovimentacao;
import com.sigfe.backend.repository.MovimentacaoFinanceiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinanceiroService {

    private final MovimentacaoFinanceiraRepository repository;

    public FinanceiroService(MovimentacaoFinanceiraRepository repository) {
        this.repository = repository;
    }

    /*
     * Registra entrada de dinheiro (VENDA paga)
     */
    @Transactional
    public void registrarVendaPaga(Venda venda) {

        if (!venda.getStatus().equals(StatusVenda.PAGA)) {
            throw new IllegalStateException("Venda ainda não está paga");
        }

        MovimentacaoFinanceira movimentacao =
                new MovimentacaoFinanceira(
                        venda.getValorTotal(),
                        TipoMovimentacao.ENTRADA,
                        OrigemMovimentacao.VENDA
                );

        repository.save(movimentacao);
    }

    /*
     * Registra saída de dinheiro (COMPRA paga)
     */
    @Transactional
    public void registrarCompraPaga(Compra compra) {

        if (!compra.estaPaga()) {
            throw new IllegalStateException("Compra ainda não está paga");
        }

        MovimentacaoFinanceira movimentacao =
                new MovimentacaoFinanceira(
                        compra.getValorTotal(),
                        TipoMovimentacao.SAIDA,
                        OrigemMovimentacao.COMPRA
                );

        repository.save(movimentacao);
    }

    /*
     * Calcula o saldo atual
     */
    public BigDecimal calcularSaldo() {

        List<MovimentacaoFinanceira> movimentacoes = repository.findAll();

        return movimentacoes.stream()
                .map(m -> m.getTipo() == TipoMovimentacao.ENTRADA
                        ? m.getValor()
                        : m.getValor().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
