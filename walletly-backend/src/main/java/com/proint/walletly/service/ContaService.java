package com.proint.walletly.service;

import com.proint.walletly.dto.conta.ContaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.proint.walletly.repository.ContaRepository;

import com.proint.walletly.model.Conta;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta save(Conta conta) {

        return contaRepository.save(conta);
    }

    public Optional<Conta> findById(Long id) {
        return contaRepository.findById(id);
    }

    public Page<Conta> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta update(Long id, Conta contaDetails) {
        return contaRepository.findById(id).map(conta -> {
            conta.setApelido(contaDetails.getApelido());
            conta.setTipoConta(contaDetails.getTipoConta());
            conta.setSaldoAtual(contaDetails.getSaldoAtual());
            conta.setDataUltimaSincronizacao(contaDetails.getDataUltimaSincronizacao());
            return contaRepository.save(conta);
        }).orElseThrow(() -> new RuntimeException("Conta não encontrada com o ID " + id));
    }

    public void deleteById(Long id) {
        contaRepository.deleteById(id);
    }
}