package com.andersonkich.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andersonkich.cursomc.services.DBservice;

@Configuration//Para ser um arquivo de configuçao
@Profile("prod")//indica meu profile
public class ProdConfig {

	@Autowired
	DBservice dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")//Acessa application-dev.properties
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateDatabase();
		return true;
	}
}
