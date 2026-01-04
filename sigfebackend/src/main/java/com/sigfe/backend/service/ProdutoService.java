package com.sigfe.backend.service;

import com.sigfe.backend.dto.produto.ProdutoCreateDTO;
import com.sigfe.backend.model.Categoria;
import com.sigfe.backend.model.Produto;
import com.sigfe.backend.repository.CategoriaRepository;
import com.sigfe.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto salvar(ProdutoCreateDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto(
                dto.nome(),
                dto.marca(),
                dto.preco(),
                dto.quantidade(),
                dto.validade(),
                categoria
        );

        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }
}



/*
* CRIACAO de camada Service para a entidade produto
* Implementacao de metodos salva, listar, buscar por Id e remover produtos
* O Service é o intermediário que organiza o fluxo: ele pega os dados que o Controller recebeu,
*  aplica as regras necessárias e manda o Repository salvar.*/