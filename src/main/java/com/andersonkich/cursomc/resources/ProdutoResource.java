package com.andersonkich.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersonkich.cursomc.domain.Produto;
import com.andersonkich.cursomc.dto.ProdutoDto;
import com.andersonkich.cursomc.resources.utils.Url;
import com.andersonkich.cursomc.services.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDto>> findPage(//Valores padrão caso não seja informado
			@RequestParam(value = "nome", defaultValue = "")String nome, 
			@RequestParam(value = "categorias", defaultValue = "0")String categorias, 
			@RequestParam(value = "page", defaultValue = "0")Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		
		List<Integer> ids = Url.decodeIntList(categorias);
		String nomeDecoder =  Url.decodeParam(nome);
		Page<Produto> list = service.search(nomeDecoder, ids, page, linePerPage, orderBy, direction);
		Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));//Não precisa ser stream o Page faz a conersão automatica
		return ResponseEntity.ok().body(listDto);
	}
}
