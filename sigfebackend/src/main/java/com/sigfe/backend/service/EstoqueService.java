package com.sigfe.backend.service;

import com.sigfe.backend.model.ItemTransacao;
import com.sigfe.backend.model.Produto;
import com.sigfe.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // indica que esta classe e um Service do Spring
public class EstoqueService {

    private final ProdutoRepository produtoRepository;

    // Injeção de dependência via construtor
    public EstoqueService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


      //Entrada de estoque


    @Transactional
    public void darEntrada(ItemTransacao item) {

        Produto produto = item.getProduto();

        // A regra de negócio fica no model
        produto.adicionarEstoque(item.getQuantidade());

        produtoRepository.save(produto);
    }


     //Saída de estoque
     //Usada quando uma VENDA é realizada

    @Transactional
    public void darSaida(ItemTransacao item) {

        Produto produto = item.getProduto();

        // O proprio Produto valida se há estoque suficiente
        produto.removerEstoque(item.getQuantidade());

        produtoRepository.save(produto);
    }
}
