package com.andersonkich.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {//Mensagem da exception
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {//Causa da exception
		super(msg, cause);
	}
	
	
}
