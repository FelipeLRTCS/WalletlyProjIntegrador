package com.proint.walletly.service;

import com.proint.walletly.model.Role;
import com.proint.walletly.model.User;
import com.proint.walletly.repository.RoleRepository;
import com.proint.walletly.repository.UserRepository;
import com.proint.walletly.utils.JwtUtils;
import com.proint.walletly.utils.LoginRequest;
import com.proint.walletly.utils.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthService authService;

    private User testUser;
    private Role testRole;
    private LoginRequest loginRequest;
    private SignupRequest signupRequest;

    @BeforeEach
    void setUp() {
        testRole = new Role("ROLE_USER");
        testRole.setId(1L);

        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .nome("Test User")
                .email("test@example.com")
                .password("encodedPassword")
                .roles(new HashSet<>(Set.of(testRole)))
                .build();

        loginRequest = new LoginRequest("testuser", "password");
        signupRequest = new SignupRequest("newuser", "New User", "newuser@example.com", "password");
    }

    @Test
    void login_ShouldReturnJwtToken_WhenAuthenticationIsSuccessful() {
        Authentication mockAuthentication = mock(Authentication.class);
        UserDetails mockUserDetails = mock(UserDetails.class);
        String expectedJwtToken = "mock.jwt.token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        when(mockAuthentication.getPrincipal()).thenReturn(mockUserDetails);
        when(jwtUtils.generateToken(mockUserDetails)).thenReturn(expectedJwtToken);

        String result = authService.login(loginRequest);

        assertEquals(expectedJwtToken, result);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtils).generateToken(mockUserDetails);
    }

    @Test
    void login_ShouldThrowException_WhenAuthenticationFails() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void register_ShouldReturnSuccessMessage_WhenUserDoesNotExist() {
        Role userRole = new Role("ROLE_USER");
        userRole.setId(1L);
        String encodedPassword = "encodedPassword";

        when(userRepository.existsByUsername(signupRequest.username())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.email())).thenReturn(false);
        when(passwordEncoder.encode(signupRequest.password())).thenReturn(encodedPassword);
        when(roleService.createRoleIfNotExists("ROLE_USER")).thenReturn(userRole);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        String result = authService.register(signupRequest);

        assertEquals("User registered successfully!", result);
        verify(userRepository).existsByUsername(signupRequest.username());
        verify(userRepository).existsByEmail(signupRequest.email());
        verify(passwordEncoder).encode(signupRequest.password());
        verify(roleService).createRoleIfNotExists("ROLE_USER");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenUsernameAlreadyExists() {
        when(userRepository.existsByUsername(signupRequest.username())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.register(signupRequest));
        assertEquals("Username is already taken!", exception.getMessage());
        verify(userRepository).existsByUsername(signupRequest.username());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        when(userRepository.existsByUsername(signupRequest.username())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.email())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.register(signupRequest));
        assertEquals("Email is already in use!", exception.getMessage());
        verify(userRepository).existsByUsername(signupRequest.username());
        verify(userRepository).existsByEmail(signupRequest.email());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_ShouldAssignDefaultRole_WhenUserIsCreated() {
        Role userRole = new Role("ROLE_USER");
        userRole.setId(1L);
        String encodedPassword = "encodedPassword";

        when(userRepository.existsByUsername(signupRequest.username())).thenReturn(false);
        when(userRepository.existsByEmail(signupRequest.email())).thenReturn(false);
        when(passwordEncoder.encode(signupRequest.password())).thenReturn(encodedPassword);
        when(roleService.createRoleIfNotExists("ROLE_USER")).thenReturn(userRole);
        when(roleRepository.findByNome("ROLE_USER")).thenReturn(java.util.Optional.of(userRole));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            assertTrue(user.getAuthorities().contains(userRole));
            return user;
        });

        authService.register(signupRequest);

        verify(roleService).createRoleIfNotExists("ROLE_USER");
        verify(userRepository).save(any(User.class));
    }
}

