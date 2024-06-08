package com.robson.helpdesk.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // Declara o AuthenticationManager para gerenciar a autenticação
    private AuthenticationManager autheticationManager;
    // Declara o JWTUtil para geração e manipulação de tokens JWT
    private JWTUtil jwtUtil;

    // Construtor da classe que inicializa os campos authenticationManager e jwtUtil
    public JWTAuthenticationFilter(AuthenticationManager autheticationManager, JWTUtil jwtUtil) {
        this.autheticationManager = autheticationManager;
        this.jwtUtil = jwtUtil;
    }
}