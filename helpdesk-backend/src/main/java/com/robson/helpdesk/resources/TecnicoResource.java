package com.robson.helpdesk.resources;

import com.robson.helpdesk.domain.Tecnico;
import com.robson.helpdesk.domain.dtos.TecnicoDTO;
import com.robson.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    //Método para buscar um tecnico pelo ID
    // Mapeia solicitações HTTP GET para este endpoint
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    //Método que busca por todos os técnicos no banco
    // Mapeia solicitações HTTP GET para este endpoint
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        //Conversao de list de Tecnico para list TecnicoDTO
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    //Método que cria um novo técnico
    // Restringe o acesso ao endpoint de exclusão apenas para usuários com a função 'ADMIN'
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping // Mapeia solicitações HTTP POST para este endpoint
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Método que atualiza um técnico
    // Restringe o acesso ao endpoint de exclusão apenas para usuários com a função 'ADMIN'
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}") // Mapeia solicitações HTTP PUT para este endpoint
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico obj = tecnicoService.update(id, objDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    //Método que deleta um técnico
    // Restringe o acesso ao endpoint de exclusão apenas para usuários com a função 'ADMIN'
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}") // Mapeia solicitações HTTP DELETE para este endpoint
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
