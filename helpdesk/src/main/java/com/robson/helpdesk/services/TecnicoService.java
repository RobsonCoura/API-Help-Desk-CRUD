package com.robson.helpdesk.services;

import com.robson.helpdesk.domain.Pessoa;
import com.robson.helpdesk.domain.Tecnico;
import com.robson.helpdesk.domain.dtos.TecnicoDTO;
import com.robson.helpdesk.repositories.PessoaRepository;
import com.robson.helpdesk.repositories.TecnicoRepository;
import com.robson.helpdesk.services.exception.DataIntegrityViolationException;
import com.robson.helpdesk.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    //Injecao de dependencias
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //Método para buscar um tecnico pelo ID
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + id));
    }

    //Método que busca por todos os técnicos no banco
    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    //Método que cria um novo técnico
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    //Método que atualiza um técnico
    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico obj = findById(id);
        validaPorCpfEEmail(objDTO);
        obj = new Tecnico(objDTO);
        return tecnicoRepository.save(obj);
    }

    //Método que deleta um técnico
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    //Método que valida se CPF e E-mail já existe no banco de dados
    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
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
