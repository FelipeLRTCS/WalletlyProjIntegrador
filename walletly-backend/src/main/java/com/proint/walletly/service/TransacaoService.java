package java.com.proint.walletly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.com.proint.walletly.model.Transacao;
import java.com.proint.walletly.repository.TransacaoRepository;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao save(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Optional<Transacao> findById(Long id) {
        return transacaoRepository.findById(id);
    }

    public Page<Transacao> findAll(Pageable pageable) {
        return transacaoRepository.findAll(pageable);
    }

    public Transacao update(Long id, Transacao transacaoDetails) {
        return transacaoRepository.findById(id).map(transacao -> {
            transacao.setDescricao(transacaoDetails.getDescricao());
            transacao.setValor(transacaoDetails.getValor());
            transacao.setTipoTransacao(transacaoDetails.getTipoTransacao());
            transacao.setDataTransacao(transacaoDetails.getDataTransacao());
            transacao.setConta(transacaoDetails.getConta());
            transacao.setCategoria(transacaoDetails.getCategoria());
            return transacaoRepository.save(transacao);
        }).orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID " + id));
    }

    public void deleteById(Long id) {
        transacaoRepository.deleteById(id);
    }
}
