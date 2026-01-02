package com.sigfe.backend.controller;

import com.sigfe.backend.model.Fornecedor;
import com.sigfe.backend.service.FornecedorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Diz ao Spring que essa classe recebe requisições HTTP e retorna JSON
@RequestMapping("/fornecedores") // URL base: http://localhost:8080/fornecedores
public class FornecedorController {

    private final FornecedorService fornecedorService;

    // Injeção de dependência via construtor (boa prática)
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }


    // CREATE - Criar fornecedor

    @PostMapping
    public ResponseEntity<Fornecedor> criarFornecedor(
            @RequestBody Fornecedor fornecedor) {

        Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
        return ResponseEntity.ok(fornecedorSalvo);
    }


    // READ - Buscar por ID

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(
            @PathVariable Long id) {

        Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(fornecedor);
    }


    // DELETE - Remover fornecedor

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFornecedor(
            @PathVariable Long id) {

        fornecedorService.remover(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
