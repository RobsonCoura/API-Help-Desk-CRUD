package com.robson.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.robson.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	// Injecao de dependencias
	@Autowired
	private DBService dbService;

	// Método para instanciar no banco de dados
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}
}
