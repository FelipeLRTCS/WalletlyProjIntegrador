package com.proint.walletly.service;

import com.proint.walletly.model.Conta;
import com.proint.walletly.model.InstituicaoFinanceira;
import com.proint.walletly.model.User;
import com.proint.walletly.repository.ContaRepository;
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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    private Conta testConta;
    private User testUser;
    private InstituicaoFinanceira testInstituicao;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .nome("Test User")
                .email("test@example.com")
                .build();

        testInstituicao = InstituicaoFinanceira.builder()
                .id(1L)
                .nome("Banco Teste")
                .logoUrl("https://example.com/logo.png")
                .build();

        testConta = Conta.builder()
                .id(1L)
                .apelido("Conta Corrente")
                .tipoConta("CORRENTE")
                .saldoAtual(new BigDecimal("1000.00"))
                .dataUltimaSincronizacao(OffsetDateTime.now())
                .usuario(testUser)
                .instituicao(testInstituicao)
                .build();
    }

    @Test
    void save_ShouldReturnSavedConta_WhenValidContaProvided() {
        when(contaRepository.save(any(Conta.class))).thenReturn(testConta);

        Conta result = contaService.save(testConta);

        assertNotNull(result);
        assertEquals(testConta.getId(), result.getId());
        assertEquals(testConta.getApelido(), result.getApelido());
        assertEquals(testConta.getTipoConta(), result.getTipoConta());
        assertEquals(testConta.getSaldoAtual(), result.getSaldoAtual());
        verify(contaRepository).save(testConta);
    }

    @Test
    void findById_ShouldReturnConta_WhenIdExists() {
        Long id = 1L;
        when(contaRepository.findById(id)).thenReturn(Optional.of(testConta));

        Optional<Conta> result = contaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(testConta.getId(), result.get().getId());
        assertEquals(testConta.getApelido(), result.get().getApelido());
        verify(contaRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Long id = 999L;
        when(contaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Conta> result = contaService.findById(id);

        assertFalse(result.isPresent());
        verify(contaRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnPageOfContas_WhenPageableProvided() {
        List<Conta> contas = Arrays.asList(testConta);
        Page<Conta> page = new PageImpl<>(contas);
        Pageable pageable = PageRequest.of(0, 10);

        when(contaRepository.findAll(pageable)).thenReturn(page);

        Page<Conta> result = contaService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testConta.getId(), result.getContent().get(0).getId());
        verify(contaRepository).findAll(pageable);
    }

    @Test
    void update_ShouldReturnUpdatedConta_WhenIdExists() {
        Long id = 1L;
        Conta updatedConta = Conta.builder()
                .id(1L)
                .apelido("Conta Poupança Atualizada")
                .tipoConta("POUPANCA")
                .saldoAtual(new BigDecimal("2000.00"))
                .dataUltimaSincronizacao(OffsetDateTime.now().plusHours(1))
                .usuario(testUser)
                .instituicao(testInstituicao)
                .build();

        when(contaRepository.findById(id)).thenReturn(Optional.of(testConta));
        when(contaRepository.save(any(Conta.class))).thenReturn(updatedConta);

        Conta result = contaService.update(id, updatedConta);

        assertNotNull(result);
        assertEquals(updatedConta.getApelido(), result.getApelido());
        assertEquals(updatedConta.getTipoConta(), result.getTipoConta());
        assertEquals(updatedConta.getSaldoAtual(), result.getSaldoAtual());
        verify(contaRepository).findById(id);
        verify(contaRepository).save(any(Conta.class));
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 999L;
        Conta updatedConta = new Conta();

        when(contaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> contaService.update(id, updatedConta));
        assertEquals("Conta não encontrada com o ID " + id, exception.getMessage());
        verify(contaRepository).findById(id);
        verify(contaRepository, never()).save(any(Conta.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete_WhenValidIdProvided() {
        Long id = 1L;

        contaService.deleteById(id);

        verify(contaRepository).deleteById(id);
    }

    @Test
    void update_ShouldUpdateCorrectFields_WhenIdExists() {
        Long id = 1L;
        Conta updatedData = Conta.builder()
                .apelido("Novo Apelido")
                .tipoConta("INVESTIMENTO")
                .saldoAtual(new BigDecimal("5000.00"))
                .dataUltimaSincronizacao(OffsetDateTime.now().plusDays(1))
                .build();

        when(contaRepository.findById(id)).thenReturn(Optional.of(testConta));
        when(contaRepository.save(any(Conta.class)))
                .thenAnswer(invocation -> {
                    Conta conta = invocation.getArgument(0);
                    assertEquals(updatedData.getApelido(), conta.getApelido());
                    assertEquals(updatedData.getTipoConta(), conta.getTipoConta());
                    assertEquals(updatedData.getSaldoAtual(), conta.getSaldoAtual());
                    assertEquals(updatedData.getDataUltimaSincronizacao(), conta.getDataUltimaSincronizacao());
                    return conta;
                });

        contaService.update(id, updatedData);

        verify(contaRepository).findById(id);
        verify(contaRepository).save(any(Conta.class));
    }

    @Test
    void save_ShouldPreserveAllFields_WhenValidContaProvided() {
        when(contaRepository.save(any(Conta.class)))
                .thenAnswer(invocation -> {
                    Conta conta = invocation.getArgument(0);
                    assertEquals(testConta.getApelido(), conta.getApelido());
                    assertEquals(testConta.getTipoConta(), conta.getTipoConta());
                    assertEquals(testConta.getSaldoAtual(), conta.getSaldoAtual());
                    assertEquals(testConta.getDataUltimaSincronizacao(), conta.getDataUltimaSincronizacao());
                    assertEquals(testConta.getUsuario(), conta.getUsuario());
                    assertEquals(testConta.getInstituicao(), conta.getInstituicao());
                    return testConta;
                });

        Conta result = contaService.save(testConta);

        assertNotNull(result);
        verify(contaRepository).save(testConta);
    }
}

