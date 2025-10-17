package com.proint.walletly.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proint.walletly.model.Orcamento;
import com.proint.walletly.service.OrcamentoService;
import java.util.Optional;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    @Autowired
    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @PostMapping
    public ResponseEntity<Orcamento> createOrcamento(@Valid @RequestBody Orcamento orcamento) {
        Orcamento savedOrcamento = orcamentoService.save(orcamento);
        return new ResponseEntity<>(savedOrcamento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> getOrcamentoById(@PathVariable Long id) {
        Optional<Orcamento> orcamento = orcamentoService.findById(id);
        return orcamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Orcamento>> getAllOrcamentos(Pageable pageable) {
        Page<Orcamento> orcamentos = orcamentoService.findAll(pageable);
        return ResponseEntity.ok(orcamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> updateOrcamento(@PathVariable Long id, @Valid @RequestBody Orcamento orcamentoDetails) {
        Orcamento updatedOrcamento = orcamentoService.update(id, orcamentoDetails);
        return ResponseEntity.ok(updatedOrcamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrcamento(@PathVariable Long id) {
        orcamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}