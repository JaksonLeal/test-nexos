package com.test.nexos.exception;

public class DepartamentoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartamentoException() {
		super("No se pudo crear el departamento");
	}

	public DepartamentoException(String message) {
		super(message);
	}
	
}
