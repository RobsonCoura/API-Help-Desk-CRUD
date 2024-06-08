package com.robson.helpdesk.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

// Marca a classe como um componente gerenciado pelo Spring, permitindo sua injeção automática em outros lugares
@Component
public class JWTUtil {

    //Atributos
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    //Método para gerar o token
    public String generateToken(String email) {
        return Jwts.builder() //Jwts é uma classe fornecida pela biblioteca JWT, o método builder() cria um novo builder para construir um token JWT.
                .setSubject(email) // Define o "subject" (assunto) do token como sendo o email passado como argumento.
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //Define a data de expiração do token.
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()) //Converte a chave secreta (secret) para um array de bytes.
                .compact();// Esse método compact ele compacta o corpo do JWT deixando a API mas perfomática.
    }

    // Método que verifica se o token JWT é válido
    public boolean tokenValido(String token) {
        // Obtém as reivindicações (claims) do token
        Claims claims = getClaims(token);
        if (claims != null) {
            // Obtém o nome de usuário das reivindicações
            String username = claims.getSubject();
            // Obtém a data de expiração das reivindicações
            Date expirationDate = claims.getExpiration();
            // Obtém a data e hora atuais
            Date now = new Date(System.currentTimeMillis());

            // Verifica se o nome de usuário não é nulo, a data de expiração não é nula e se o token ainda não expirou
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        // Retorna falso se as condições acima não forem atendidas
        return false;
    }

    // Método que obtém as reivindicações (claims) a partir do token JWT
    private Claims getClaims(String token) {
        try {
            // Analisa o token JWT e retorna o corpo das reivindicações
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // Retorna nulo se ocorrer uma exceção ao analisar o token
            return null;
        }
    }

    // Método que obtém o nome de usuário a partir do token JWT
    public String getUsername(String token) {
        // Obtém as reivindicações do token
        Claims claims = getClaims(token);
        if (claims != null) {
            // Retorna o nome de usuário das reivindicações
            return claims.getSubject();
        }
        // Retorna nulo se as reivindicações forem nulas
        return null;
    }
}
