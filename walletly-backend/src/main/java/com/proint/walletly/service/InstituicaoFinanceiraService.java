package com.proint.walletly.service;

import com.proint.walletly.dto.instituicao.InstituicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proint.walletly.model.InstituicaoFinanceira;
import com.proint.walletly.repository.InstituicaoFinanceiraRepository;
import java.util.Optional;

@Service
public class InstituicaoFinanceiraService {

    private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    @Autowired
    public InstituicaoFinanceiraService(InstituicaoFinanceiraRepository instituicaoFinanceiraRepository) {
        this.instituicaoFinanceiraRepository = instituicaoFinanceiraRepository;
    }

    public InstituicaoFinanceira save(InstituicaoDTO instituicao) {
        var instituicaoFinanceira = InstituicaoFinanceira.builder().nome(instituicao.nome()).logoUrl(instituicao.logoUrl()).build();
        return instituicaoFinanceiraRepository.save(instituicaoFinanceira);
    }

    public Optional<InstituicaoFinanceira> findById(Long id) {
        return instituicaoFinanceiraRepository.findById(id);
    }

    public Page<InstituicaoFinanceira> findAll(Pageable pageable) {
        return instituicaoFinanceiraRepository.findAll(pageable);
    }

    public InstituicaoFinanceira update(Long id, InstituicaoFinanceira instituicaoDetails) {
        return instituicaoFinanceiraRepository.findById(id).map(instituicao -> {
            instituicao.setNome(instituicaoDetails.getNome());
            instituicao.setLogoUrl(instituicaoDetails.getLogoUrl());
            return instituicaoFinanceiraRepository.save(instituicao);
        }).orElseThrow(() -> new RuntimeException("Instituição financeira não encontrada com o ID " + id));
    }

    public void deleteById(Long id) {
        instituicaoFinanceiraRepository.deleteById(id);
    }
}