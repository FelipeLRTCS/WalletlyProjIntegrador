package com.proint.walletly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.proint.walletly.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>, JpaSpecificationExecutor<Transacao> {
}
