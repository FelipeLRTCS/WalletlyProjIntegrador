package com.proint.walletly.service;

import com.proint.walletly.model.*;
import com.proint.walletly.repository.OrcamentoRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrcamentoServiceTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

    @InjectMocks
    private OrcamentoService orcamentoService;

    private Orcamento testOrcamento;
    private User testUser;
    private Categoria testCategoria;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .nome("Test User")
                .email("test@example.com")
                .build();

        testCategoria = Categoria.builder()
                .id(1L)
                .nome("Alimentação")
                .urlImagemCategoria("https://example.com/food.png")
                .build();

        testOrcamento = Orcamento.builder()
                .id(1L)
                .valorMaximo(new BigDecimal("500.00"))
                .mes(1)
                .ano(2024)
                .user(testUser)
                .categoria(testCategoria)
                .build();
    }

    @Test
    void save_ShouldReturnSavedOrcamento_WhenValidOrcamentoProvided() {
        when(orcamentoRepository.save(any(Orcamento.class))).thenReturn(testOrcamento);

        Orcamento result = orcamentoService.save(testOrcamento);

        assertNotNull(result);
        assertEquals(testOrcamento.getId(), result.getId());
        assertEquals(testOrcamento.getValorMaximo(), result.getValorMaximo());
        assertEquals(testOrcamento.getMes(), result.getMes());
        assertEquals(testOrcamento.getAno(), result.getAno());
        verify(orcamentoRepository).save(testOrcamento);
    }

    @Test
    void findById_ShouldReturnOrcamento_WhenIdExists() {
        Long id = 1L;
        when(orcamentoRepository.findById(id)).thenReturn(Optional.of(testOrcamento));

        Optional<Orcamento> result = orcamentoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(testOrcamento.getId(), result.get().getId());
        assertEquals(testOrcamento.getValorMaximo(), result.get().getValorMaximo());
        verify(orcamentoRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Long id = 999L;
        when(orcamentoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Orcamento> result = orcamentoService.findById(id);

        assertFalse(result.isPresent());
        verify(orcamentoRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnPageOfOrcamentos_WhenPageableProvided() {
        List<Orcamento> orcamentos = Arrays.asList(testOrcamento);
        Page<Orcamento> page = new PageImpl<>(orcamentos);
        Pageable pageable = PageRequest.of(0, 10);

        when(orcamentoRepository.findAll(pageable)).thenReturn(page);

        Page<Orcamento> result = orcamentoService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testOrcamento.getId(), result.getContent().get(0).getId());
        verify(orcamentoRepository).findAll(pageable);
    }

    @Test
    void update_ShouldReturnUpdatedOrcamento_WhenIdExists() {
        Long id = 1L;
        Orcamento updatedOrcamento = Orcamento.builder()
                .id(1L)
                .valorMaximo(new BigDecimal("750.00"))
                .mes(2)
                .ano(2024)
                .user(testUser)
                .categoria(testCategoria)
                .build();

        when(orcamentoRepository.findById(id)).thenReturn(Optional.of(testOrcamento));
        when(orcamentoRepository.save(any(Orcamento.class))).thenReturn(updatedOrcamento);

        Orcamento result = orcamentoService.update(id, updatedOrcamento);

        assertNotNull(result);
        assertEquals(updatedOrcamento.getValorMaximo(), result.getValorMaximo());
        assertEquals(updatedOrcamento.getMes(), result.getMes());
        assertEquals(updatedOrcamento.getAno(), result.getAno());
        verify(orcamentoRepository).findById(id);
        verify(orcamentoRepository).save(any(Orcamento.class));
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 999L;
        Orcamento updatedOrcamento = new Orcamento();

        when(orcamentoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> orcamentoService.update(id, updatedOrcamento));
        assertEquals("Orçamento não encontrado com o ID " + id, exception.getMessage());
        verify(orcamentoRepository).findById(id);
        verify(orcamentoRepository, never()).save(any(Orcamento.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete_WhenValidIdProvided() {
        Long id = 1L;

        orcamentoService.deleteById(id);

        verify(orcamentoRepository).deleteById(id);
    }

    @Test
    void update_ShouldUpdateCorrectFields_WhenIdExists() {
        Long id = 1L;
        Orcamento updatedData = Orcamento.builder()
                .valorMaximo(new BigDecimal("1000.00"))
                .mes(3)
                .ano(2024)
                .build();

        when(orcamentoRepository.findById(id)).thenReturn(Optional.of(testOrcamento));
        when(orcamentoRepository.save(any(Orcamento.class)))
                .thenAnswer(invocation -> {
                    Orcamento orcamento = invocation.getArgument(0);
                    assertEquals(updatedData.getValorMaximo(), orcamento.getValorMaximo());
                    assertEquals(updatedData.getMes(), orcamento.getMes());
                    assertEquals(updatedData.getAno(), orcamento.getAno());
                    return orcamento;
                });

        orcamentoService.update(id, updatedData);

        verify(orcamentoRepository).findById(id);
        verify(orcamentoRepository).save(any(Orcamento.class));
    }

    @Test
    void save_ShouldPreserveAllFields_WhenValidOrcamentoProvided() {
        when(orcamentoRepository.save(any(Orcamento.class)))
                .thenAnswer(invocation -> {
                    Orcamento orcamento = invocation.getArgument(0);
                    assertEquals(testOrcamento.getValorMaximo(), orcamento.getValorMaximo());
                    assertEquals(testOrcamento.getMes(), orcamento.getMes());
                    assertEquals(testOrcamento.getAno(), orcamento.getAno());
                    assertEquals(testOrcamento.getUser(), orcamento.getUser());
                    assertEquals(testOrcamento.getCategoria(), orcamento.getCategoria());
                    return testOrcamento;
                });

        Orcamento result = orcamentoService.save(testOrcamento);

        assertNotNull(result);
        verify(orcamentoRepository).save(testOrcamento);
    }

    @Test
    void update_ShouldUpdateUserAndCategoria_WhenIdExists() {
        Long id = 1L;
        User newUser = User.builder().id(2L).username("newuser").build();
        Categoria newCategoria = Categoria.builder().id(2L).nome("Nova Categoria").build();
        
        Orcamento updatedData = Orcamento.builder()
                .user(newUser)
                .categoria(newCategoria)
                .build();

        when(orcamentoRepository.findById(id)).thenReturn(Optional.of(testOrcamento));
        when(orcamentoRepository.save(any(Orcamento.class)))
                .thenAnswer(invocation -> {
                    Orcamento orcamento = invocation.getArgument(0);
                    assertEquals(updatedData.getUser(), orcamento.getUser());
                    assertEquals(updatedData.getCategoria(), orcamento.getCategoria());
                    return orcamento;
                });

        orcamentoService.update(id, updatedData);

        verify(orcamentoRepository).findById(id);
        verify(orcamentoRepository).save(any(Orcamento.class));
    }

    @Test
    void update_ShouldUpdateAllFields_WhenIdExists() {
        Long id = 1L;
        User newUser = User.builder().id(2L).username("newuser").build();
        Categoria newCategoria = Categoria.builder().id(2L).nome("Nova Categoria").build();
        
        Orcamento updatedData = Orcamento.builder()
                .valorMaximo(new BigDecimal("2000.00"))
                .mes(12)
                .ano(2025)
                .user(newUser)
                .categoria(newCategoria)
                .build();

        when(orcamentoRepository.findById(id)).thenReturn(Optional.of(testOrcamento));
        when(orcamentoRepository.save(any(Orcamento.class)))
                .thenAnswer(invocation -> {
                    Orcamento orcamento = invocation.getArgument(0);
                    assertEquals(updatedData.getValorMaximo(), orcamento.getValorMaximo());
                    assertEquals(updatedData.getMes(), orcamento.getMes());
                    assertEquals(updatedData.getAno(), orcamento.getAno());
                    assertEquals(updatedData.getUser(), orcamento.getUser());
                    assertEquals(updatedData.getCategoria(), orcamento.getCategoria());
                    return orcamento;
                });

        orcamentoService.update(id, updatedData);

        verify(orcamentoRepository).findById(id);
        verify(orcamentoRepository).save(any(Orcamento.class));
    }
}

