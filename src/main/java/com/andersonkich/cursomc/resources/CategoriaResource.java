package com.andersonkich.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.services.CategoriaService;



@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody Categoria obj){//Object no lugar do void
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		/*Uri é uma boa pratica de programação, função padrão que devolve a uri do novo obj inserido 
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") pega a uri que chama o post e adiciona o id na requisição
		 * buildAndExpand(obj.getId()).toUri() atribui o id do novo obj inserido
		 * @RequestBody Converte obj para Json automaticamente
		 */
		}
		

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();//noContent() retorna vazio
	}
	
	
	
}
