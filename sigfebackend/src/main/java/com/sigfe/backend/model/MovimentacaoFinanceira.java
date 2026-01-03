package com.sigfe.backend.model;

import com.sigfe.backend.model.enums.OrigemMovimentacao;
import com.sigfe.backend.model.enums.TipoMovimentacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimentacoes_financeiras")
public class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigemMovimentacao origem;

    protected MovimentacaoFinanceira() {}

    public MovimentacaoFinanceira(BigDecimal valor,
                                  TipoMovimentacao tipo,
                                  OrigemMovimentacao origem) {

        this.valor = valor;
        this.tipo = tipo;
        this.origem = origem;
        this.data = LocalDate.now();
    }

    // Getters
    public BigDecimal getValor() { return valor; }
    public TipoMovimentacao getTipo() { return tipo; }
    public OrigemMovimentacao getOrigem() { return origem; }
    public LocalDate getData() { return data; }
}
