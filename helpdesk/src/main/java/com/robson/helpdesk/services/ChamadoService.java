package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Chamado;
import com.robson.helpdesk.repositories.ChamadoRepository;
import com.robson.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    //Injecao de dependencias
    @Autowired
    private ChamadoRepository repository;

    //Método para buscar um chamado pelo ID
    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }
}
