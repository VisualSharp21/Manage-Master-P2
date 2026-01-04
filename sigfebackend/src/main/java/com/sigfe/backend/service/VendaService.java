package com.sigfe.backend.service;

import com.sigfe.backend.model.Venda;
import com.sigfe.backend.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Venda salvar(Venda venda) {

        // 🔹 Validações de regra de negócio
        if (venda.getFormaPagamento() == null) {
            throw new IllegalArgumentException("Forma de pagamento é obrigatória");
        }

        if (venda.getNumeroDocumento() == null || venda.getNumeroDocumento().isBlank()) {
            throw new IllegalArgumentException("Número do documento é obrigatório");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda deve conter pelo menos um item");
        }

        // 🔹 Validação e vínculo dos itens
        venda.getItens().forEach(item -> {

            if (item.getProduto() == null) {
                throw new IllegalArgumentException("Item sem produto");
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida");
            }

            if (item.getPreco() == null || item.getPreco().signum() <= 0) {
                throw new IllegalArgumentException("Preço inválido");
            }

            // 🔥 vínculo reverso
            item.setTransacao(venda);
        });

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    @Transactional
    public Venda atualizar(Long id, Venda vendaAtualizada) {

        Venda venda = buscarPorId(id);

        venda.setFornecedor(vendaAtualizada.getFornecedor());
        venda.setFormaPagamento(vendaAtualizada.getFormaPagamento());
        venda.setNumeroDocumento(vendaAtualizada.getNumeroDocumento());
        venda.setStatus(vendaAtualizada.getStatus());
        venda.setItens(vendaAtualizada.getItens());

        // 🔹 Reassociar itens
        venda.getItens().forEach(item -> item.setTransacao(venda));

        return vendaRepository.save(venda);
    }

    @Transactional
    public void deletar(Long id) {
        vendaRepository.deleteById(id);
    }
}

/*
 * CRIACAO de camada Service para a entidade produto
 * Implementacao de metodos salva, listar, buscar por Id e remover produtos
 * O Service é o intermediário que organiza o fluxo: ele pega os dados que o Controller recebeu,
 *  aplica as regras necessárias e manda o Repository salvar.*/
