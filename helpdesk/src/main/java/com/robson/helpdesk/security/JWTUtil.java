package com.robson.helpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

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
}
