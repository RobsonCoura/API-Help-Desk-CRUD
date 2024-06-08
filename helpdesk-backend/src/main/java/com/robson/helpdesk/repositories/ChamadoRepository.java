package com.robson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robson.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
