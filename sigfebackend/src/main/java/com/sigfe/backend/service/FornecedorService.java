package com.sigfe.backend.service;

import com.sigfe.backend.model.Fornecedor;
import com.sigfe.backend.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Fornecedor salvar(Fornecedor fornecedor) {

        // Garantir que o ID não venha preenchido
        fornecedor.setId(null);

        // Validações de regra de negócio
        if (fornecedor.getNome() == null || fornecedor.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (fornecedor.getTelefone() == null || fornecedor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }

        return repository.save(fornecedor);
    }

    public Fornecedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Fornecedor não encontrado");
        }
        repository.deleteById(id);
    }
}

/*
 * CRIACAO de camada Service para a entidade produto
 * Implementacao de metodos salva, listar, buscar por Id e remover produtos
 * O Service é o intermediário que organiza o fluxo: ele pega os dados que o Controller recebeu,
 *  aplica as regras necessárias e manda o Repository salvar.*/
