package com.proint.walletly.controller;

import com.proint.walletly.dto.instituicao.InstituicaoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proint.walletly.service.InstituicaoFinanceiraService;
import java.util.Optional;

@RestController
@RequestMapping("/instituicoes-financeiras")
public class InstituicaoFinanceiraController {

    private final InstituicaoFinanceiraService instituicaoFinanceiraService;

    @Autowired
    public InstituicaoFinanceiraController(InstituicaoFinanceiraService instituicaoFinanceiraService) {
        this.instituicaoFinanceiraService = instituicaoFinanceiraService;
    }

    @PostMapping
    public ResponseEntity<InstituicaoDTO> createInstituicaoFinanceira(@Valid @RequestBody InstituicaoDTO instituicao) {
        InstituicaoDTO savedInstituicao = instituicaoFinanceiraService.save(instituicao);
        return new ResponseEntity<>(savedInstituicao, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDTO> getInstituicaoFinanceiraById(@PathVariable Long id) {
        Optional<InstituicaoDTO> instituicao = instituicaoFinanceiraService.findById(id);
        return instituicao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<InstituicaoDTO>> getAllInstituicoesFinanceiras(Pageable pageable) {
        Page<InstituicaoDTO> instituicoes = instituicaoFinanceiraService.findAll(pageable);
        return ResponseEntity.ok(instituicoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoDTO> updateInstituicaoFinanceira(@PathVariable Long id, @Valid @RequestBody InstituicaoDTO instituicaoDetails) {
        InstituicaoDTO updatedInstituicao = instituicaoFinanceiraService.update(id, instituicaoDetails);
        return ResponseEntity.ok(updatedInstituicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstituicaoFinanceira(@PathVariable Long id) {
        instituicaoFinanceiraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}