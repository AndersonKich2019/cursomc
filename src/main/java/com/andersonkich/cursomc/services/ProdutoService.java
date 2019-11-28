package com.andersonkich.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.domain.Produto;
import com.andersonkich.cursomc.repositories.CategoriaRepository;
import com.andersonkich.cursomc.repositories.ProdutoRepository;
import com.andersonkich.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	
}
