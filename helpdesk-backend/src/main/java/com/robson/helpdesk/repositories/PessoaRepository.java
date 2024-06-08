package com.robson.helpdesk.repositories;

import com.robson.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    //Método de busca por CPF no banco
    Optional<Pessoa> findByCpf(String cpf);

    //Método de busca por E-mail no banco
    Optional<Pessoa> findByEmail(String email);
}
