package com.sigfe.backend.controller;

import com.sigfe.backend.dto.produto.ProdutoCreateDTO;
import com.sigfe.backend.dto.produto.ProdutoResponseDTO;
import com.sigfe.backend.model.Produto;
import com.sigfe.backend.service.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // 🔹 CREATE
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(
            @RequestBody ProdutoCreateDTO dto) {

        Produto produto = produtoService.salvar(dto);
        return ResponseEntity.ok(new ProdutoResponseDTO(produto));
    }

    // 🔹 READ - LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {

        return ResponseEntity.ok(
                produtoService.listarTodos()
                        .stream()
                        .map(ProdutoResponseDTO::new)
                        .toList()
        );
    }

    // 🔹 READ - BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {

        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(new ProdutoResponseDTO(produto));

    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {

        produtoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}


/*
O Controller é responsável por:

Receber requisicoes HTTP (GET, POST, DELETE, etc.)

Chamar o Service

Retornar respostas HTTP (JSON + status)
* */
