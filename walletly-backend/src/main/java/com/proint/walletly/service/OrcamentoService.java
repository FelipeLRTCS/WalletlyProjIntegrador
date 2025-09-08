package java.com.proint.walletly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.com.proint.walletly.model.Orcamento;
import java.com.proint.walletly.repository.OrcamentoRepository;
import java.util.Optional;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;

    @Autowired
    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public Orcamento save(Orcamento orcamento) {
        return orcamentoRepository.save(orcamento);
    }

    public Optional<Orcamento> findById(Long id) {
        return orcamentoRepository.findById(id);
    }

    public Page<Orcamento> findAll(Pageable pageable) {
        return orcamentoRepository.findAll(pageable);
    }

    public Orcamento update(Long id, Orcamento orcamentoDetails) {
        return orcamentoRepository.findById(id).map(orcamento -> {
            orcamento.setValorMaximo(orcamentoDetails.getValorMaximo());
            orcamento.setMes(orcamentoDetails.getMes());
            orcamento.setAno(orcamentoDetails.getAno());
            orcamento.setUsuario(orcamentoDetails.getUsuario());
            orcamento.setCategoria(orcamentoDetails.getCategoria());
            return orcamentoRepository.save(orcamento);
        }).orElseThrow(() -> new RuntimeException("Orçamento não encontrado com o ID " + id));
    }

    public void deleteById(Long id) {
        orcamentoRepository.deleteById(id);
    }
}