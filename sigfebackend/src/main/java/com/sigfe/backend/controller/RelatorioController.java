package com.sigfe.backend.controller;

import com.sigfe.backend.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    /*
     * Relatório financeiro: saldo atual
     */
    @GetMapping("/financeiro/saldo")
    public ResponseEntity<BigDecimal> saldoFinanceiro() {
        return ResponseEntity.ok(relatorioService.obterSaldoAtual());
    }

    /*
     * Relatório de estoque: produtos com estoque baixo
     */
    @GetMapping("/estoque/baixo")
    public ResponseEntity<?> estoqueBaixo() {
        return ResponseEntity.ok(relatorioService.produtosComEstoqueBaixo());
    }

    /*
     * Relatório de vendas: total vendido
     */
    @GetMapping("/vendas/total")
    public ResponseEntity<BigDecimal> totalVendas() {
        return ResponseEntity.ok(relatorioService.totalVendido());
    }
}

/*
O Controller é responsável por:

Receber requisicoes HTTP (GET, POST, DELETE, etc.)

Chamar o Service

Retornar respostas HTTP (JSON + status)
* */

