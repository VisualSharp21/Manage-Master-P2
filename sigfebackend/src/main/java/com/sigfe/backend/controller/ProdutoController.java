package com.sigfe.backend.controller;

import com.sigfe.backend.model.Produto;
import com.sigfe.backend.service.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Indica que esta classe e um controller
@RequestMapping("/produtos") // Define url base para endpoints desse controller
                             // Endpoint e um endereco url que recebe a requisicao http, executa uma acao e devolve a resposta

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService)
    {
        this.produtoService = produtoService;
    }

    // CRIAR PRODUTO

    @PostMapping //Mapeia requisicoes POST para /produtos
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto)
    {
        Produto produtosalvo = produtoService.salvar(produto);
        return ResponseEntity.ok(produtosalvo);
    }

    // READ - LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos ()
    {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    // READ - BUSCAR POR ID

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {

        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrou, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrou, 404
    }

    // DELETE - REMOVER PRODUTO

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long id) {

        produtoService.remover(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }


}

/*
O Controller é responsável por:

Receber requisicoes HTTP (GET, POST, DELETE, etc.)

Chamar o Service

Retornar respostas HTTP (JSON + status)
* */
