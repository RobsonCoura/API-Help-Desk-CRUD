package com.robson.helpdesk.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Classe JWTAuthorizationFilter que estende BasicAuthenticationFilter para tratar autorização baseada em JWT
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    // Declaração dos objetos utilitários JWT e UserDetailsService
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // Construtor para inicializar os objetos jwtUtil e userDetailsService
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager); // Chamada ao construtor da classe pai
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // Método principal para filtrar cada solicitação HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Obtém o cabeçalho "Authorization" da solicitação
        String header = request.getHeader("Authorization");
        // Verifica se o cabeçalho não é nulo e começa com "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            // Obtém o token de autenticação a partir do cabeçalho
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
            // Se o token de autenticação não for nulo, define-o no contexto de segurança
            if (authToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continua a cadeia de filtros
        chain.doFilter(request, response);
    }

    // Método auxiliar para obter a autenticação com base no token JWT
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // Verifica se o token é válido
        if (jwtUtil.tokenValido(token)) {
            // Obtém o nome de usuário a partir do token
            String username = jwtUtil.getUsername(token);
            // Carrega os detalhes do usuário pelo nome de usuário
            UserDetails details = userDetailsService.loadUserByUsername(username);
            // Retorna o token de autenticação com os detalhes do usuário
            return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
        }
        // Retorna nulo se o token não for válido
        return null;
    }
}
