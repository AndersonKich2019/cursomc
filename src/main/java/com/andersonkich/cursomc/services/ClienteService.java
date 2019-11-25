package com.andersonkich.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = repository.findAll();
		return list;
	}
	
	
}
