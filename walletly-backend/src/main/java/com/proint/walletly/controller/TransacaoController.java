package java.com.proint.walletly.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.com.proint.walletly.model.Transacao;
import java.com.proint.walletly.service.TransacaoService;
import java.util.Optional;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Transacao> createTransacao(@Valid @RequestBody Transacao transacao) {
        Transacao savedTransacao = transacaoService.save(transacao);
        return new ResponseEntity<>(savedTransacao, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getTransacaoById(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.findById(id);
        return transacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Transacao>> getAllTransacoes(Pageable pageable) {
        Page<Transacao> transacoes = transacaoService.findAll(pageable);
        return ResponseEntity.ok(transacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> updateTransacao(@PathVariable Long id, @Valid @RequestBody Transacao transacaoDetails) {
        Transacao updatedTransacao = transacaoService.update(id, transacaoDetails);
        return ResponseEntity.ok(updatedTransacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}