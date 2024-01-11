package com.robson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robson.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
