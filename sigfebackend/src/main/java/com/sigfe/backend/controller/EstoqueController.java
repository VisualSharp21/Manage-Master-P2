package com.sigfe.backend.controller;

import com.sigfe.backend.model.ItemTransacao;
import com.sigfe.backend.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// Indica que esta classe é um Controller REST
@RequestMapping("/estoque")
// Define a rota base: http://localhost:8080/estoque
public class EstoqueController {

    private final EstoqueService estoqueService;

    // Injeção de dependência via construtor (boa prática)
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    /*
     * ENTRADA DE ESTOQUE
     * Usado quando uma COMPRA é registrada
     */
    @PostMapping("/entrada")
    public ResponseEntity<String> entradaEstoque(@RequestBody ItemTransacao item) {

        estoqueService.darEntrada(item);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Entrada de estoque realizada com sucesso");
    }

    /*
     * Saida DE ESTOQUE
     * Usado quando uma VENDA é realizada
     */
    @PostMapping("/saida")
    public ResponseEntity<String> saidaEstoque(@RequestBody ItemTransacao item) {

        estoqueService.darSaida(item);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Saída de estoque realizada com sucesso");
    }
}

/*
O Controller é responsável por:

Receber requisicoes HTTP (GET, POST, DELETE, etc.)

Chamar o Service

Retornar respostas HTTP (JSON + status)
* */
