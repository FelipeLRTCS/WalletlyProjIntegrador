package com.proint.walletly.service;

import com.proint.walletly.model.Role;
import com.proint.walletly.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role testRole;

    @BeforeEach
    void setUp() {
        testRole = new Role("ROLE_USER");
        testRole.setId(1L);
    }

    @Test
    void findByName_ShouldReturnRole_WhenRoleExists() {
        String roleName = "ROLE_USER";
        when(roleRepository.findByNome(roleName)).thenReturn(Optional.of(testRole));

        Role result = roleService.findByName(roleName);

        assertNotNull(result);
        assertEquals(testRole.getId(), result.getId());
        assertEquals(testRole.getNome(), result.getNome());
        verify(roleRepository).findByNome(roleName);
    }

    @Test
    void findByName_ShouldThrowException_WhenRoleDoesNotExist() {
        String roleName = "ROLE_ADMIN";
        when(roleRepository.findByNome(roleName)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> roleService.findByName(roleName));
        assertEquals("Role not found: " + roleName, exception.getMessage());
        verify(roleRepository).findByNome(roleName);
    }

    @Test
    void createRoleIfNotExists_ShouldReturnExistingRole_WhenRoleAlreadyExists() {
        String roleName = "ROLE_USER";
        when(roleRepository.findByNome(roleName)).thenReturn(Optional.of(testRole));

        Role result = roleService.createRoleIfNotExists(roleName);

        assertNotNull(result);
        assertEquals(testRole.getId(), result.getId());
        assertEquals(testRole.getNome(), result.getNome());
        verify(roleRepository).findByNome(roleName);
        verify(roleRepository, never()).save(any(Role.class));
    }

    @Test
    void createRoleIfNotExists_ShouldCreateNewRole_WhenRoleDoesNotExist() {
        String roleName = "ROLE_ADMIN";
        Role newRole = new Role(roleName);
        newRole.setId(2L);

        when(roleRepository.findByNome(roleName)).thenReturn(Optional.empty());
        when(roleRepository.save(any(Role.class))).thenReturn(newRole);

        Role result = roleService.createRoleIfNotExists(roleName);

        assertNotNull(result);
        assertEquals(newRole.getId(), result.getId());
        assertEquals(newRole.getNome(), result.getNome());
        verify(roleRepository).findByNome(roleName);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void createRoleIfNotExists_ShouldCreateRoleWithCorrectName_WhenRoleDoesNotExist() {
        String roleName = "ROLE_MANAGER";
        when(roleRepository.findByNome(roleName)).thenReturn(Optional.empty());
        when(roleRepository.save(any(Role.class))).thenAnswer(invocation -> {
            Role role = invocation.getArgument(0);
            assertEquals(roleName, role.getNome());
            role.setId(3L);
            return role;
        });

        Role result = roleService.createRoleIfNotExists(roleName);

        assertNotNull(result);
        assertEquals(roleName, result.getNome());
        verify(roleRepository).save(any(Role.class));
    }
}

