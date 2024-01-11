package com.robson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robson.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
