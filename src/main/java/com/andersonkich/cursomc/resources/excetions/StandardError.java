package com.andersonkich.cursomc.resources.excetions;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long timestap;
	private Integer status;
	private String message;
	private String path;

	public StandardError(Long timestap, Integer status, String message, String path) {
		super();
		this.timestap = timestap;
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public Long getTimestap() {
		return timestap;
	}

	public void setTimestap(Long timestap) {
		this.timestap = timestap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
