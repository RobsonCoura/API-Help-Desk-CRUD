package com.robson.helpdesk.security;

import com.robson.helpdesk.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpringSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    //Atributos
    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    // Define um construtor para inicializar os campos da classe. Recebe como parâmetros o id, senha, email e as autoridades do usuário.
    public UserSpringSecurity(Integer id, String email, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
    }

    //Método que retorna a coleção de autoridades do usuário.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    //Método que retorna o senha do usuário
    @Override
    public String getPassword() {
        return senha;
    }

    //Método que retorna o email do usuário
    @Override
    public String getUsername() {
        return email;
    }

    //Método que indica se a conta não está expirada.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Método que indica se a conta não está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Método que indica se as credenciais não estão expiradas.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Método que indica se a conta do usuário está ativa ou não.
    @Override
    public boolean isEnabled() {
        return true;
    }

}
