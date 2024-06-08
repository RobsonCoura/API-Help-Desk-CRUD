package com.robson.helpdesk.config;

import com.robson.helpdesk.security.JWTAuthenticationFilter;
import com.robson.helpdesk.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};

    @Autowired
    private Environment env;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    //Método configure para proteger os endpoints contra vulnerabilidade
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Verifica se o perfil ativo é 'test' e desabilita as opções de frame para permitir o uso do H2 console
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        // Configura CORS e desabilita CSRF (Cross-Site Request Forgery)
        http.cors().and().csrf().disable();
        // Adiciona o filtro de autenticação JWT ao processo de segurança
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        // Configura as regras de autorização para as requisições HTTP
        http.authorizeRequests()
                // Permite acesso irrestrito aos endpoints definidos em PUBLIC_MATCHERS
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                // Requer autenticação para qualquer outra requisição
                .anyRequest().authenticated();
        // Configura a política de gerenciamento de sessões para ser stateless (sem estado)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configura o AuthenticationManagerBuilder para usar o serviço de detalhes do usuário (userDetailsService)
        // e um codificador de senha (bCryptPasswordEncoder)
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //Método para liberar o CorsConfiguration para dar acesso as requisições
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Cria uma nova configuração CORS com valores padrão permitidos
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        // Define os métodos HTTP permitidos para CORS
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        // Cria uma fonte de configuração CORS baseada em URLs
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Registra a configuração CORS para todos os caminhos (/**)
        source.registerCorsConfiguration("/**", configuration);
        // Retorna a fonte de configuração CORS
        return source;
    }

    //Método para criptografar a senha antes de gravar no banco
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
