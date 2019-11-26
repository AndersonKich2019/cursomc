package com.andersonkich.cursomc.resources.excetions;

import java.util.ArrayList;
import java.util.List;



public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Long timestap, Integer status,String message, String path) {
		super(timestap, status, message, path);
		
	}

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String nome, String message) {
		erros.add(new FieldMessage(nome, message));
	}
	
	
}
