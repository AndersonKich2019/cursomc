package com.andersonkich.cursomc.services.exceptions;

public class ArgumentNotValidException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ArgumentNotValidException(String msg) {//Mensagem da exception
		super(msg);
	}
	
	public ArgumentNotValidException(String msg, Throwable cause) {//Causa da exception
		super(msg, cause);
	}
	
	
}
