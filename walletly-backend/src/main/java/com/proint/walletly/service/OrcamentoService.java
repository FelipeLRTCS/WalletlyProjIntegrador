package com.proint.walletly.service;

import com.proint.walletly.dto.orcamento.OrcamentoDTO;
import com.proint.walletly.mapper.OrcamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proint.walletly.model.Orcamento;
import com.proint.walletly.repository.OrcamentoRepository;
import java.util.Optional;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final OrcamentoMapper orcamentoMapper;

    @Autowired
    public OrcamentoService(OrcamentoRepository orcamentoRepository, OrcamentoMapper orcamentoMapper) {
        this.orcamentoRepository = orcamentoRepository;
        this.orcamentoMapper = orcamentoMapper;
    }

    public OrcamentoDTO save(OrcamentoDTO dto) {
        Orcamento orcamento = orcamentoMapper.toEntity(dto);
        Orcamento saved = orcamentoRepository.save(orcamento);
        return orcamentoMapper.toDTO(saved);
    }

    public Optional<OrcamentoDTO> findById(Long id) {
        return orcamentoRepository.findById(id)
                .map(orcamentoMapper::toDTO);
    }

    public Page<OrcamentoDTO> findAll(Pageable pageable) {
        return orcamentoRepository.findAll(pageable)
                .map(orcamentoMapper::toDTO);
    }

    public OrcamentoDTO update(Long id, OrcamentoDTO dto) {
        Orcamento orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com o ID " + id));
        orcamentoMapper.updateEntityFromDTO(dto, orcamento);
        Orcamento updated = orcamentoRepository.save(orcamento);
        return orcamentoMapper.toDTO(updated);
    }

    public void deleteById(Long id) {
        orcamentoRepository.deleteById(id);
    }
}