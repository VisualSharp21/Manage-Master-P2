package com.sigfe.backend.service;

import com.sigfe.backend.model.Compra;
import com.sigfe.backend.model.ItemTransacao;
import com.sigfe.backend.repository.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Transactional
    public Compra salvar(Compra compra) {

        // 🔹 Validação da compra
        if (compra.getUsuario() == null || compra.getUsuario().isBlank()) {
            throw new IllegalArgumentException("Usuário é obrigatório");
        }

        if (compra.getItens() == null || compra.getItens().isEmpty()) {
            throw new IllegalArgumentException("A compra deve ter pelo menos um item");
        }

        // 🔹 Validação e vínculo dos itens
        for (ItemTransacao item : compra.getItens()) {

            if (item.getProduto() == null) {
                throw new IllegalArgumentException("Item sem produto");
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida");
            }

            if (item.getPreco() == null || item.getPreco().signum() <= 0) {
                throw new IllegalArgumentException("Preço inválido");
            }

            // 🔥 ESSENCIAL: vínculo reverso
            item.setTransacao(compra);
        }

        return compraRepository.save(compra);
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    @Transactional
    public Compra atualizar(Long id, Compra compraAtualizada) {

        Compra compra = buscarPorId(id);

        compra.setUsuario(compraAtualizada.getUsuario());
        compra.setFormaPagamento(compraAtualizada.getFormaPagamento());
        compra.setFornecedor(compraAtualizada.getFornecedor());
        compra.setItens(compraAtualizada.getItens());

        // 🔹 Reassociar os itens
        for (ItemTransacao item : compra.getItens()) {
            item.setTransacao(compra);
        }

        return compraRepository.save(compra);
    }

    @Transactional
    public void marcarComoPaga(Long id) {
        Compra compra = buscarPorId(id);
        compra.marcarComoPaga();
        compraRepository.save(compra);
    }

    @Transactional
    public void cancelar(Long id) {
        Compra compra = buscarPorId(id);
        compra.cancelar();
        compraRepository.save(compra);
    }

    @Transactional
    public void deletar(Long id) {
        compraRepository.deleteById(id);
    }
}

