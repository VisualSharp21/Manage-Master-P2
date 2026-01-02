package com.sigfe.backend.repository;

import com.sigfe.backend.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}

/*No Spring Boot, um Repository (Repositório) é uma camada
de abstração que serve para gerenciar o acesso aos dados.
Ele funciona como uma "ponte" entre
na aplicação e no banco de dados.*/