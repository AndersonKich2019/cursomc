package com.andersonkich.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
}
