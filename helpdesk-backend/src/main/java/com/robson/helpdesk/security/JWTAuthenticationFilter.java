package com.robson.helpdesk.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robson.helpdesk.domain.dtos.CredenciaisDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

    // Método chamado quando a autenticação é bem-sucedida
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Obtém o nome de usuário a partir do principal do objeto de autenticação
        String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
        // Gera um token JWT para o usuário autenticado
        String token = jwtUtil.generateToken(username);
        // Define cabeçalhos na resposta para expor o token JWT
        response.setHeader("access-control-expose-headers", "Authorization");
        // Adiciona o token JWT ao cabeçalho Authorization
        response.setHeader("Authorization", "Bearer " + token);
    }

    // Método chamado quando a autenticação falha
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // Define o status da resposta para 401 (Não autorizado)
        response.setStatus(401);
        // Define o tipo de conteúdo da resposta como JSON
        response.setContentType("application/json");
        // Escreve uma mensagem de erro JSON no corpo da resposta
        response.getWriter().append(json());
    }

    // Método auxiliar que cria uma mensagem de erro em formato JSON
    private CharSequence json() {
        // Obtém o timestamp atual
        long date = new Date().getTime();
        // Retorna a mensagem de erro JSON
        return "{"
                + "\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha invalidos\", "
                + "\"path\": \"/login\"}";
    }
}
