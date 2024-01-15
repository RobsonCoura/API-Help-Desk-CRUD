package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Tecnico;
import com.robson.helpdesk.repositories.TecnicoRepository;
import com.robson.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    //Injecao de dependencias
    @Autowired
    private TecnicoRepository tecnicoRepository;

    //Método para buscar um tecnico pelo ID
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + id));
    }

    //Método que busca por todos os técnicos no banco
    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
