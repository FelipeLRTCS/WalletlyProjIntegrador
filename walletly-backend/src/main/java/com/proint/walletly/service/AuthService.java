package com.proint.walletly.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proint.walletly.model.User;
import com.proint.walletly.repository.UserRepository;
import com.proint.walletly.utils.JwtResponse;
import com.proint.walletly.utils.JwtUtils;
import com.proint.walletly.utils.LoginRequest;
import com.proint.walletly.utils.SignupRequest;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    
    private UserRepository userRepository;
    
    private PasswordEncoder passwordEncoder;
    
    private final JwtUtils jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), 
                        loginRequest.password())
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        
        return new JwtResponse(jwt, userDetails.getUsername());
    }
    
    public String register(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.username())) {
            throw new RuntimeException("Username is already taken!");
        }
        
        if (userRepository.existsByEmail(signupRequest.email())) {
            throw new RuntimeException("Email is already in use!");
        }
        
        User user = new User(
                signupRequest.username(),
                signupRequest.email(),
                passwordEncoder.encode(signupRequest.password())
        );
        
        userRepository.save(user);
        return "User registered successfully!";
    }
}
