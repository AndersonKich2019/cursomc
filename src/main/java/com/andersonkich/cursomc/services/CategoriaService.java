package com.andersonkich.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	
	public List<Categoria> findAll(){
		List<Categoria> list = repository.findAll();
		return list;
	}
	
}
