package com.andersonkich.cursomc.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersonkich.cursomc.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> list =  Arrays.asList(cat1,cat2);
		return list;
	}
	
}
