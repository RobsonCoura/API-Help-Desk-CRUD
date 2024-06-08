package com.robson.helpdesk.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robson.helpdesk.domain.dtos.CredenciaisDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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

    // Método que tenta autenticar o usuário com base nas credenciais fornecidas na requisição
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Lê as credenciais do corpo da requisição e mapeia para o objeto CredenciaisDTO
            CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            // Cria um token de autenticação com o email e senha extraídos das credenciais
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
            // Autentica o token utilizando o authenticationManager
            Authentication authentication = autheticationManager.authenticate(authenticationToken);
            // Retorna o objeto de autenticação se bem-sucedido
            return authentication;
        } catch (Exception e) {
            // Lança uma RuntimeException em caso de falha
            throw new RuntimeException(e);
        }
    }
    
}
