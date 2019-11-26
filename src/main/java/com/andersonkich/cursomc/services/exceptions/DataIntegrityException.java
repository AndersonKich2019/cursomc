package com.andersonkich.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {//Mensagem da exception
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {//Causa da exception
		super(msg, cause);
	}
	
	
}
