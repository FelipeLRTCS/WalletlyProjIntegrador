package com.proint.walletly.controller;

import com.proint.walletly.dto.conta.ContaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proint.walletly.model.Conta;
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
    public ResponseEntity<Conta> createConta(@Valid @RequestBody Conta conta) {
        Conta savedConta = contaService.save(conta);
        return new ResponseEntity<>(savedConta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable Long id) {
        Optional<Conta> conta = contaService.findById(id);
        return conta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> getAllContas(Pageable pageable) {
        Page<Conta> contas = contaService.findAll(pageable);
        return ResponseEntity.ok(contas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Long id, @Valid @RequestBody Conta contaDetails) {
        Conta updatedConta = contaService.update(id, contaDetails);
        return ResponseEntity.ok(updatedConta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}