package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Chamado;
import com.robson.helpdesk.domain.Cliente;
import com.robson.helpdesk.domain.Tecnico;
import com.robson.helpdesk.domain.dtos.ChamadoDTO;
import com.robson.helpdesk.domain.enums.Prioridade;
import com.robson.helpdesk.domain.enums.Status;
import com.robson.helpdesk.repositories.ChamadoRepository;
import com.robson.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    //Injecao de dependencias
    @Autowired
    private ChamadoRepository repository;

    //Injecao de dependencias
    @Autowired
    private TecnicoService tecnicoService;

    //Injecao de dependencias
    @Autowired
    private ClienteService clienteService;

    //Método para buscar um chamado pelo ID
    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    //Método para buscar uma lista de chamados
    public List<Chamado> findAll() {
        return repository.findAll();
    }

    //Método para cadastrar chamado
    public Chamado create(@Valid ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));
    }

    //Método para atualizar um chamado
    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj =  findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }

}
