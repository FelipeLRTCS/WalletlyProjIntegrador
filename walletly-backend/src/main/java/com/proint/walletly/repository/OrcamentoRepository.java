package com.proint.walletly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proint.walletly.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
