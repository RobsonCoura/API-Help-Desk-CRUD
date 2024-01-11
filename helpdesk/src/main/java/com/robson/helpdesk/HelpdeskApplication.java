package com.robson.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.robson.helpdesk.domain.Chamado;
import com.robson.helpdesk.domain.Cliente;
import com.robson.helpdesk.domain.Tecnico;
import com.robson.helpdesk.domain.enums.Perfil;
import com.robson.helpdesk.domain.enums.Prioridade;
import com.robson.helpdesk.domain.enums.Status;
import com.robson.helpdesk.repositories.ChamadoRepository;
import com.robson.helpdesk.repositories.ClienteRepository;
import com.robson.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	// Injecao de dependencias
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Criacao das instancias de Tecnico | Cliente | Chamado
		Tecnico tec1 = new Tecnico(null, "Robson Dev", "63653230268", "robson@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);

		// Salvar as instancias no banco de dados
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
