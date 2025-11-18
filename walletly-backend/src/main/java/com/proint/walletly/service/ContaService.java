package com.proint.walletly.service;

import com.proint.walletly.dto.conta.ContaDTO;
import com.proint.walletly.mapper.ContaMapper;
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
    private final ContaMapper contaMapper;

    @Autowired
    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    public ContaDTO save(ContaDTO dto) {
        Conta conta = contaMapper.toEntity(dto);
        Conta saved = contaRepository.save(conta);
        return contaMapper.toDTO(saved);
    }

    public Optional<ContaDTO> findById(Long id) {
        return contaRepository.findById(id)
                .map(contaMapper::toDTO);
    }

    public Page<ContaDTO> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable)
                .map(contaMapper::toDTO);
    }

    public ContaDTO update(Long id, ContaDTO dto) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com o ID " + id));
        contaMapper.updateEntityFromDTO(dto, conta);
        Conta updated = contaRepository.save(conta);
        return contaMapper.toDTO(updated);
    }

    public void deleteById(Long id) {
        contaRepository.deleteById(id);
    }
}