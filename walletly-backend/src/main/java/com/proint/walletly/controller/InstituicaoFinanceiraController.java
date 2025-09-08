package java.com.proint.walletly.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.com.proint.walletly.model.InstituicaoFinanceira;
import java.com.proint.walletly.service.InstituicaoFinanceiraService;
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
    public ResponseEntity<InstituicaoFinanceira> createInstituicaoFinanceira(@Valid @RequestBody InstituicaoFinanceira instituicao) {
        InstituicaoFinanceira savedInstituicao = instituicaoFinanceiraService.save(instituicao);
        return ResponseEntity.ok(savedInstituicao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoFinanceira> getInstituicaoFinanceiraById(@PathVariable Long id) {
        Optional<InstituicaoFinanceira> instituicao = instituicaoFinanceiraService.findById(id);
        return instituicao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<InstituicaoFinanceira>> getAllInstituicoesFinanceiras(Pageable pageable) {
        Page<InstituicaoFinanceira> instituicoes = instituicaoFinanceiraService.findAll(pageable);
        return ResponseEntity.ok(instituicoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoFinanceira> updateInstituicaoFinanceira(@PathVariable Long id, @Valid @RequestBody InstituicaoFinanceira instituicaoDetails) {
        InstituicaoFinanceira updatedInstituicao = instituicaoFinanceiraService.update(id, instituicaoDetails);
        return ResponseEntity.ok(updatedInstituicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstituicaoFinanceira(@PathVariable Long id) {
        instituicaoFinanceiraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}