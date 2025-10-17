package com.proint.walletly.service;

import com.proint.walletly.model.Categoria;
import com.proint.walletly.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria testCategoria;

    @BeforeEach
    void setUp() {
        testCategoria = Categoria.builder()
                .id(1L)
                .nome("Alimentação")
                .urlImagemCategoria("https://example.com/food.png")
                .build();
    }

    @Test
    void save_ShouldReturnSavedCategoria_WhenValidCategoriaProvided() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(testCategoria);

        Categoria result = categoriaService.save(testCategoria);

        assertNotNull(result);
        assertEquals(testCategoria.getId(), result.getId());
        assertEquals(testCategoria.getNome(), result.getNome());
        assertEquals(testCategoria.getUrlImagemCategoria(), result.getUrlImagemCategoria());
        verify(categoriaRepository).save(testCategoria);
    }

    @Test
    void findById_ShouldReturnCategoria_WhenIdExists() {
        Long id = 1L;
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(testCategoria));

        Optional<Categoria> result = categoriaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(testCategoria.getId(), result.get().getId());
        assertEquals(testCategoria.getNome(), result.get().getNome());
        assertEquals(testCategoria.getUrlImagemCategoria(), result.get().getUrlImagemCategoria());
        verify(categoriaRepository).findById(id);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenIdDoesNotExist() {
        Long id = 999L;
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Categoria> result = categoriaService.findById(id);

        assertFalse(result.isPresent());
        verify(categoriaRepository).findById(id);
    }

    @Test
    void findAll_ShouldReturnPageOfCategorias_WhenPageableProvided() {
        List<Categoria> categorias = Arrays.asList(testCategoria);
        Page<Categoria> page = new PageImpl<>(categorias);
        Pageable pageable = PageRequest.of(0, 10);

        when(categoriaRepository.findAll(pageable)).thenReturn(page);

        Page<Categoria> result = categoriaService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testCategoria.getId(), result.getContent().get(0).getId());
        verify(categoriaRepository).findAll(pageable);
    }

    @Test
    void update_ShouldReturnUpdatedCategoria_WhenIdExists() {
        Long id = 1L;
        Categoria updatedCategoria = Categoria.builder()
                .id(1L)
                .nome("Transporte")
                .urlImagemCategoria("https://example.com/transport.png")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(testCategoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(updatedCategoria);

        Categoria result = categoriaService.update(id, updatedCategoria);

        assertNotNull(result);
        assertEquals(updatedCategoria.getNome(), result.getNome());
        assertEquals(updatedCategoria.getUrlImagemCategoria(), result.getUrlImagemCategoria());
        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void update_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 999L;
        Categoria updatedCategoria = new Categoria();

        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> categoriaService.update(id, updatedCategoria));
        assertEquals("Categoria não encontrada com o ID " + id, exception.getMessage());
        verify(categoriaRepository).findById(id);
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete_WhenValidIdProvided() {
        Long id = 1L;

        categoriaService.deleteById(id);

        verify(categoriaRepository).deleteById(id);
    }

    @Test
    void update_ShouldUpdateCorrectFields_WhenIdExists() {
        Long id = 1L;
        Categoria updatedData = Categoria.builder()
                .nome("Entretenimento")
                .urlImagemCategoria("https://example.com/entertainment.png")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(testCategoria));
        when(categoriaRepository.save(any(Categoria.class)))
                .thenAnswer(invocation -> {
                    Categoria categoria = invocation.getArgument(0);
                    assertEquals(updatedData.getNome(), categoria.getNome());
                    assertEquals(updatedData.getUrlImagemCategoria(), categoria.getUrlImagemCategoria());
                    return categoria;
                });

        categoriaService.update(id, updatedData);

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void save_ShouldPreserveAllFields_WhenValidCategoriaProvided() {
        when(categoriaRepository.save(any(Categoria.class)))
                .thenAnswer(invocation -> {
                    Categoria categoria = invocation.getArgument(0);
                    assertEquals(testCategoria.getNome(), categoria.getNome());
                    assertEquals(testCategoria.getUrlImagemCategoria(), categoria.getUrlImagemCategoria());
                    return testCategoria;
                });

        Categoria result = categoriaService.save(testCategoria);

        assertNotNull(result);
        verify(categoriaRepository).save(testCategoria);
    }

    @Test
    void findAll_ShouldReturnMultipleCategorias_WhenMultipleCategoriasExist() {
        Categoria categoria2 = Categoria.builder()
                .id(2L)
                .nome("Transporte")
                .urlImagemCategoria("https://example.com/transport.png")
                .build();

        List<Categoria> categorias = Arrays.asList(testCategoria, categoria2);
        Page<Categoria> page = new PageImpl<>(categorias);
        Pageable pageable = PageRequest.of(0, 10);

        when(categoriaRepository.findAll(pageable)).thenReturn(page);

        Page<Categoria> result = categoriaService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        verify(categoriaRepository).findAll(pageable);
    }

    @Test
    void update_ShouldUpdateOnlyNome_WhenOnlyNomeProvided() {
        Long id = 1L;
        Categoria updatedData = Categoria.builder()
                .nome("Nova Categoria")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(testCategoria));
        when(categoriaRepository.save(any(Categoria.class)))
                .thenAnswer(invocation -> {
                    Categoria categoria = invocation.getArgument(0);
                    assertEquals(updatedData.getNome(), categoria.getNome());
                    // URL should remain unchanged
                    assertEquals(testCategoria.getUrlImagemCategoria(), categoria.getUrlImagemCategoria());
                    return categoria;
                });

        categoriaService.update(id, updatedData);

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void update_ShouldUpdateOnlyUrlImagem_WhenOnlyUrlImagemProvided() {
        Long id = 1L;
        Categoria updatedData = Categoria.builder()
                .urlImagemCategoria("https://example.com/new-image.png")
                .build();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(testCategoria));
        when(categoriaRepository.save(any(Categoria.class)))
                .thenAnswer(invocation -> {
                    Categoria categoria = invocation.getArgument(0);
                    assertEquals(updatedData.getUrlImagemCategoria(), categoria.getUrlImagemCategoria());
                    // Nome should remain unchanged
                    assertEquals(testCategoria.getNome(), categoria.getNome());
                    return categoria;
                });

        categoriaService.update(id, updatedData);

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).save(any(Categoria.class));
    }
}

