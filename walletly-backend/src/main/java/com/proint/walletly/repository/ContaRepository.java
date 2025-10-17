package com.proint.walletly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proint.walletly.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}

