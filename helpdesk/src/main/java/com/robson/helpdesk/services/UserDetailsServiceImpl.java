package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Pessoa;
import com.robson.helpdesk.repositories.PessoaRepository;
import com.robson.helpdesk.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    // Este método recebe um email e carrega os detalhes do usuário correspondente. Pode lançar uma exceção UsernameNotFoundException se o usuário não for encontrado.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email); // Verifica se o Optional contém um valor (ou seja, se um usuário com o email fornecido foi encontrado).
        if (user.isPresent()) {
            return new UserSpringSecurity(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis()); // Se o usuário estiver presente, cria e retorna uma nova instância, passa-se o ID, email, senha e perfis (roles) do usuário encontrado.
        }
        throw new UsernameNotFoundException(email); // Se o usuário não for encontrado, lança uma exceção com o email fornecido, indicando que o usuário não pôde ser encontrado.
    }
}
