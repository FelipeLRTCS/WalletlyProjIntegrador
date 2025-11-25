package com.proint.walletly.service;

import com.proint.walletly.dto.categoria.CategoriaDTO;
import com.proint.walletly.mapper.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proint.walletly.model.Categoria;
import com.proint.walletly.repository.CategoriaRepository;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria saved = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(saved);
    }

    public Optional<CategoriaDTO> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO);
    }

    public Page<CategoriaDTO> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(categoriaMapper::toDTO);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID " + id));
        categoriaMapper.updateEntityFromDTO(dto, categoria);
        Categoria updated = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(updated);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
