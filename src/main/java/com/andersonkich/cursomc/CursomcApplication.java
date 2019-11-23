package com.andersonkich.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {//CommandLineRunner usado para instanciar obetos ao iniciar a aplicação

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {//Metodo do CommandLineRunner
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		
		repository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
