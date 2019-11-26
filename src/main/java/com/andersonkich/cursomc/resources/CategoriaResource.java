package com.andersonkich.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.dto.CategoriaDto;
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
	public ResponseEntity<List<CategoriaDto>> findAll(){
		List<Categoria> list = service.findAll();
		List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@Valid @RequestBody CategoriaDto objDto){//Object no lugar do void
		Categoria obj = service.fromDto(objDto);
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
	public ResponseEntity<Object> update(@Valid @RequestBody CategoriaDto objDto, @PathVariable Integer id){
		Categoria obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();//noContent() retorna vazio
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDto>> findPage(//Valores padrão caso não seja informado
			@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Categoria> list = service.findPage(page, linePerPage, orderBy, direction);
		Page<CategoriaDto> listDto = list.map(obj -> new CategoriaDto(obj));//Não precisa ser stream o Page faz a conersão automatica
		return ResponseEntity.ok().body(listDto);
		
	}
	
}
