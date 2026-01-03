package com.sigfe.backend.repository;

import com.sigfe.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByDataTransacaoBetween(LocalDate inicio, LocalDate fim);
}

/*No Spring Boot, um Repository (Repositório) é uma camada
de abstração que serve para gerenciar o acesso aos dados.
Ele funciona como uma "ponte" entre
na aplicação e no banco de dados.*/