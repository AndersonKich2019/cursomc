package com.andersonkich.cursomc.dto;

import java.io.Serializable;

import com.andersonkich.cursomc.domain.Produto;

public class ProdutoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	
	public ProdutoDto() {
		
	}
	
	public ProdutoDto(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
