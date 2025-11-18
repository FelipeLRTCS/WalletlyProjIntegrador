package com.proint.walletly.controller;

import com.proint.walletly.dto.conta.ContaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proint.walletly.service.ContaService;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> createConta(@Valid @RequestBody ContaDTO conta) {
        ContaDTO savedConta = contaService.save(conta);
        return new ResponseEntity<>(savedConta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> getContaById(@PathVariable Long id) {
        Optional<ContaDTO> conta = contaService.findById(id);
        return conta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ContaDTO>> getAllContas(Pageable pageable) {
        Page<ContaDTO> contas = contaService.findAll(pageable);
        return ResponseEntity.ok(contas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> updateConta(@PathVariable Long id, @Valid @RequestBody ContaDTO contaDetails) {
        ContaDTO updatedConta = contaService.update(id, contaDetails);
        return ResponseEntity.ok(updatedConta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}