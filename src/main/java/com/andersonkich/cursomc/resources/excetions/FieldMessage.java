package com.andersonkich.cursomc.resources.excetions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String message;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String nome, String message) {
		super();
		this.nome = nome;
		this.message = message;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
