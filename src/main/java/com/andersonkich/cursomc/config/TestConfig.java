package com.andersonkich.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andersonkich.cursomc.services.DBservice;

@Configuration//Para ser um arquivo de configuçao
@Profile("test")//indica meu profile
public class TestConfig {

	@Autowired
	DBservice dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateDatabase();
		return true;
	}
}
