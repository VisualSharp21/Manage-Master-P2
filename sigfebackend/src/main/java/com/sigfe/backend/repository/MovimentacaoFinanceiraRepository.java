package com.sigfe.backend.repository;

import com.sigfe.backend.model.MovimentacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoFinanceiraRepository extends JpaRepository <MovimentacaoFinanceira, Long> {
}
