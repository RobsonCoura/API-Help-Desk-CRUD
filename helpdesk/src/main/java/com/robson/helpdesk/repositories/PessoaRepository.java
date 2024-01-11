package com.robson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robson.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
