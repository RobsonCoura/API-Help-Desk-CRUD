package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Cliente;
import com.robson.helpdesk.domain.Pessoa;
import com.robson.helpdesk.domain.dtos.ClienteDTO;
import com.robson.helpdesk.repositories.ClienteRepository;
import com.robson.helpdesk.repositories.PessoaRepository;
import com.robson.helpdesk.services.exception.DataIntegrityViolationException;
import com.robson.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    //Injecao de dependencias
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    //Método para buscar um cliente pelo ID
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + id));
    }

    //Método que busca por todos os clientes no banco
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    //Método que cria um novo cliente
    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return clienteRepository.save(newObj);
    }

    //Método que atualiza um cliente
    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente obj = findById(id);
        validaPorCpfEEmail(objDTO);
        obj = new Cliente(objDTO);
        return clienteRepository.save(obj);
    }

    //Método que deleta um cliente
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    //Método que valida se CPF e E-mail já existe no banco de dados
    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        //Verificacao se CPF existe
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
