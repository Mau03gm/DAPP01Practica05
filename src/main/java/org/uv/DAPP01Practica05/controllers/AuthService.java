package org.uv.DAPP01Practica05.controllers;

import org.uv.DAPP01Practica05.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.uv.DAPP01Practica05.requests.AuthResponse;
import org.uv.DAPP01Practica05.requests.LoginRequest;
import org.uv.DAPP01Practica05.requests.RegisterRequest;

import org.uv.DAPP01Practica05.entity.Role;
import org.uv.DAPP01Practica05.entity.User;
import org.uv.DAPP01Practica05.entity.UserRepository;

/**
 *
 * @author yodoeaoffi06
 */

@Service
@RequiredArgsConstructor
class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), 
                        request.getPassword()
                ));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtservice.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        
        userRepository.save(user);
        
        return AuthResponse.builder()
                .token(jwtservice.getToken(user))
                .build();
    } 
}
