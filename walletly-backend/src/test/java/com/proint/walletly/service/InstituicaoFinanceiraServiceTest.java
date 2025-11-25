package com.proint.walletly.service;

import com.proint.walletly.dto.instituicao.InstituicaoDTO;
import com.proint.walletly.model.InstituicaoFinanceira;
import com.proint.walletly.repository.InstituicaoFinanceiraRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstituicaoFinanceiraServiceTest {

    @Mock
    private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    @InjectMocks
    private InstituicaoFinanceiraService instituicaoFinanceiraService;

    private InstituicaoFinanceira testInstituicao;
    private InstituicaoDTO testInstituicaoDTO;

    @BeforeEach
    void setUp() {
        testInstituicao = InstituicaoFinanceira.builder()
                .id(1L)
                .nome("Banco Teste")
                .logoUrl("https://example.com/logo.png")
                .build();

        testInstituicaoDTO = new InstituicaoDTO("Banco Teste", "https://example.com/logo.png");
    }

    @Test
    void save_ShouldReturnSavedInstituicao_WhenValidDTOProvided() {
        when(instituicaoFinanceiraRepository.save(any(InstituicaoFinanceira.class))).thenReturn(testInstituicao);

        InstituicaoFinanceira result = instituicaoFinanceiraService.save(testInstituicaoDTO);

        assertNotNull(result);
        assertEquals(testInstituicao.getId(), result.getId());
        assertEquals(testInstituicao.getNome(), result.getNome());
        assertEquals(testInstituicao.getLogoUrl(), result.getLogoUrl());
        verify(instituicaoFinanceiraRepository).save(any(InstituicaoFinanceira.class));
    }

    @Test
    void save_ShouldCreateInstituicaoWithCorrectData_WhenValidDTOProvided() {
        when(instituicaoFinanceiraRepository.save(any(InstituicaoFinanceira.class)))
                .thenAnswer(invocation -> {
                    InstituicaoFinanceira instituicao = invocation.getArgument(0);
                    assertEquals(testInstituicaoDTO.nome(), instituicao.getNome());
                    assertEquals(testInstituicaoDTO.logoUrl(), instituicao.getLogoUrl());
                    instituicao.setId(1L);
                    return instituicao;
                });

        InstituicaoFinanceira result = instituicaoFinanceiraService.save(testInstituicaoDTO);

        assertNotNull(result);
        assertEquals(testInstituicaoDTO.nome(), result.getNome());
        assertEquals(testInstituicaoDTO.logoUrl(), result.getLogoUrl());
        verify(instituicaoFinanceiraRepository).save(any(InstituicaoFinanceira.class));
    }

    @Test
    void findById_ShouldReturnInstituicao_WhenIdExists() {
        Long id = 1L;
        when(instituicaoFinanceiraRepository.findById(id)).thenReturn(Optional.of(testInstituicao));

        Optional<InstituicaoFinanceira> result = instituicaoFinanceiraService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(testInstituicao.getId(), result.get().getId());
        assertEquals(testInstituicao.getNome(), result.get().getNome());
        verify(instituicaoFinanceiraRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Long id = 999L;
        when(instituicaoFinanceiraRepository.findById(id)).thenReturn(Optional.empty());

        Optional<InstituicaoFinanceira> result = instituicaoFinanceiraService.findById(id);

        assertFalse(result.isPresent());
        verify(instituicaoFinanceiraRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnPageOfInstituicoes_WhenPageableProvided() {
        List<InstituicaoFinanceira> instituicoes = Arrays.asList(testInstituicao);
        Page<InstituicaoFinanceira> page = new PageImpl<>(instituicoes);
        Pageable pageable = PageRequest.of(0, 10);

        when(instituicaoFinanceiraRepository.findAll(pageable)).thenReturn(page);

        Page<InstituicaoFinanceira> result = instituicaoFinanceiraService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testInstituicao.getId(), result.getContent().get(0).getId());
        verify(instituicaoFinanceiraRepository).findAll(pageable);
    }

    @Test
    void update_ShouldReturnUpdatedInstituicao_WhenIdExists() {
        Long id = 1L;
        InstituicaoFinanceira updatedInstituicao = InstituicaoFinanceira.builder()
                .id(1L)
                .nome("Banco Atualizado")
                .logoUrl("https://example.com/new-logo.png")
                .build();

        when(instituicaoFinanceiraRepository.findById(id)).thenReturn(Optional.of(testInstituicao));
        when(instituicaoFinanceiraRepository.save(any(InstituicaoFinanceira.class))).thenReturn(updatedInstituicao);

        InstituicaoFinanceira result = instituicaoFinanceiraService.update(id, updatedInstituicao);

        assertNotNull(result);
        assertEquals(updatedInstituicao.getNome(), result.getNome());
        assertEquals(updatedInstituicao.getLogoUrl(), result.getLogoUrl());
        verify(instituicaoFinanceiraRepository).findById(id);
        verify(instituicaoFinanceiraRepository).save(any(InstituicaoFinanceira.class));
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 999L;
        InstituicaoFinanceira updatedInstituicao = new InstituicaoFinanceira();

        when(instituicaoFinanceiraRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> instituicaoFinanceiraService.update(id, updatedInstituicao));
        assertEquals("Instituição financeira não encontrada com o ID " + id, exception.getMessage());
        verify(instituicaoFinanceiraRepository).findById(id);
        verify(instituicaoFinanceiraRepository, never()).save(any(InstituicaoFinanceira.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete_WhenValidIdProvided() {
        Long id = 1L;

        instituicaoFinanceiraService.deleteById(id);

        verify(instituicaoFinanceiraRepository).deleteById(id);
    }

    @Test
    void update_ShouldUpdateCorrectFields_WhenIdExists() {
        Long id = 1L;
        InstituicaoFinanceira updatedData = InstituicaoFinanceira.builder()
                .nome("Novo Nome")
                .logoUrl("https://example.com/new-logo.png")
                .build();

        when(instituicaoFinanceiraRepository.findById(id)).thenReturn(Optional.of(testInstituicao));
        when(instituicaoFinanceiraRepository.save(any(InstituicaoFinanceira.class)))
                .thenAnswer(invocation -> {
                    InstituicaoFinanceira instituicao = invocation.getArgument(0);
                    assertEquals(updatedData.getNome(), instituicao.getNome());
                    assertEquals(updatedData.getLogoUrl(), instituicao.getLogoUrl());
                    return instituicao;
                });

        instituicaoFinanceiraService.update(id, updatedData);

        verify(instituicaoFinanceiraRepository).findById(id);
        verify(instituicaoFinanceiraRepository).save(any(InstituicaoFinanceira.class));
    }
}

