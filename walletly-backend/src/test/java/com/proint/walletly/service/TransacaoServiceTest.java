package com.proint.walletly.service;

import com.proint.walletly.model.*;
import com.proint.walletly.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    private Transacao testTransacao;
    private Conta testConta;
    private Categoria testCategoria;

    @BeforeEach
    void setUp() {
        testConta = Conta.builder()
                .id(1L)
                .apelido("Conta Teste")
                .tipoConta("CORRENTE")
                .saldoAtual(new BigDecimal("1000.00"))
                .build();

        testCategoria = Categoria.builder()
                .id(1L)
                .nome("Alimentação")
                .urlImagemCategoria("https://example.com/food.png")
                .build();

        testTransacao = Transacao.builder()
                .id(1L)
                .descricao("Compra no supermercado")
                .valor(new BigDecimal("50.00"))
                .tipoTransacao("DESPESA")
                .dataTransacao(LocalDate.now())
                .conta(testConta)
                .categoria(testCategoria)
                .build();
    }

    @Test
    void save_ShouldReturnSavedTransacao_WhenValidTransacaoProvided() {
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(testTransacao);

        Transacao result = transacaoService.save(testTransacao);

        assertNotNull(result);
        assertEquals(testTransacao.getId(), result.getId());
        assertEquals(testTransacao.getDescricao(), result.getDescricao());
        assertEquals(testTransacao.getValor(), result.getValor());
        assertEquals(testTransacao.getTipoTransacao(), result.getTipoTransacao());
        assertEquals(testTransacao.getDataTransacao(), result.getDataTransacao());
        verify(transacaoRepository).save(testTransacao);
    }

    @Test
    void findById_ShouldReturnTransacao_WhenIdExists() {
        Long id = 1L;
        when(transacaoRepository.findById(id)).thenReturn(Optional.of(testTransacao));

        Optional<Transacao> result = transacaoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(testTransacao.getId(), result.get().getId());
        assertEquals(testTransacao.getDescricao(), result.get().getDescricao());
        verify(transacaoRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Long id = 999L;
        when(transacaoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Transacao> result = transacaoService.findById(id);

        assertFalse(result.isPresent());
        verify(transacaoRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnPageOfTransacoes_WhenPageableProvided() {
        List<Transacao> transacoes = Arrays.asList(testTransacao);
        Page<Transacao> page = new PageImpl<>(transacoes);
        Pageable pageable = PageRequest.of(0, 10);

        when(transacaoRepository.findAll(pageable)).thenReturn(page);

        Page<Transacao> result = transacaoService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testTransacao.getId(), result.getContent().get(0).getId());
        verify(transacaoRepository).findAll(pageable);
    }

    @Test
    void update_ShouldReturnUpdatedTransacao_WhenIdExists() {
        Long id = 1L;
        Transacao updatedTransacao = Transacao.builder()
                .id(1L)
                .descricao("Compra atualizada")
                .valor(new BigDecimal("75.00"))
                .tipoTransacao("RECEITA")
                .dataTransacao(LocalDate.now().plusDays(1))
                .conta(testConta)
                .categoria(testCategoria)
                .build();

        when(transacaoRepository.findById(id)).thenReturn(Optional.of(testTransacao));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(updatedTransacao);

        Transacao result = transacaoService.update(id, updatedTransacao);

        assertNotNull(result);
        assertEquals(updatedTransacao.getDescricao(), result.getDescricao());
        assertEquals(updatedTransacao.getValor(), result.getValor());
        assertEquals(updatedTransacao.getTipoTransacao(), result.getTipoTransacao());
        assertEquals(updatedTransacao.getDataTransacao(), result.getDataTransacao());
        verify(transacaoRepository).findById(id);
        verify(transacaoRepository).save(any(Transacao.class));
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 999L;
        Transacao updatedTransacao = new Transacao();

        when(transacaoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> transacaoService.update(id, updatedTransacao));
        assertEquals("Transação não encontrada com o ID " + id, exception.getMessage());
        verify(transacaoRepository).findById(id);
        verify(transacaoRepository, never()).save(any(Transacao.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete_WhenValidIdProvided() {
        Long id = 1L;

        transacaoService.deleteById(id);

        verify(transacaoRepository).deleteById(id);
    }

    @Test
    void update_ShouldUpdateCorrectFields_WhenIdExists() {
        Long id = 1L;
        Transacao updatedData = Transacao.builder()
                .descricao("Nova descrição")
                .valor(new BigDecimal("100.00"))
                .tipoTransacao("DESPESA")
                .dataTransacao(LocalDate.now().plusDays(2))
                .build();

        when(transacaoRepository.findById(id)).thenReturn(Optional.of(testTransacao));
        when(transacaoRepository.save(any(Transacao.class)))
                .thenAnswer(invocation -> {
                    Transacao transacao = invocation.getArgument(0);
                    assertEquals(updatedData.getDescricao(), transacao.getDescricao());
                    assertEquals(updatedData.getValor(), transacao.getValor());
                    assertEquals(updatedData.getTipoTransacao(), transacao.getTipoTransacao());
                    assertEquals(updatedData.getDataTransacao(), transacao.getDataTransacao());
                    return transacao;
                });

        transacaoService.update(id, updatedData);

        verify(transacaoRepository).findById(id);
        verify(transacaoRepository).save(any(Transacao.class));
    }

    @Test
    void save_ShouldPreserveAllFields_WhenValidTransacaoProvided() {
        when(transacaoRepository.save(any(Transacao.class)))
                .thenAnswer(invocation -> {
                    Transacao transacao = invocation.getArgument(0);
                    assertEquals(testTransacao.getDescricao(), transacao.getDescricao());
                    assertEquals(testTransacao.getValor(), transacao.getValor());
                    assertEquals(testTransacao.getTipoTransacao(), transacao.getTipoTransacao());
                    assertEquals(testTransacao.getDataTransacao(), transacao.getDataTransacao());
                    assertEquals(testTransacao.getConta(), transacao.getConta());
                    assertEquals(testTransacao.getCategoria(), transacao.getCategoria());
                    return testTransacao;
                });

        Transacao result = transacaoService.save(testTransacao);

        assertNotNull(result);
        verify(transacaoRepository).save(testTransacao);
    }

    @Test
    void update_ShouldUpdateContaAndCategoria_WhenIdExists() {
        Long id = 1L;
        Conta newConta = Conta.builder().id(2L).apelido("Nova Conta").build();
        Categoria newCategoria = Categoria.builder().id(2L).nome("Nova Categoria").build();
        
        Transacao updatedData = Transacao.builder()
                .conta(newConta)
                .categoria(newCategoria)
                .build();

        when(transacaoRepository.findById(id)).thenReturn(Optional.of(testTransacao));
        when(transacaoRepository.save(any(Transacao.class)))
                .thenAnswer(invocation -> {
                    Transacao transacao = invocation.getArgument(0);
                    assertEquals(updatedData.getConta(), transacao.getConta());
                    assertEquals(updatedData.getCategoria(), transacao.getCategoria());
                    return transacao;
                });

        transacaoService.update(id, updatedData);

        verify(transacaoRepository).findById(id);
        verify(transacaoRepository).save(any(Transacao.class));
    }
}

