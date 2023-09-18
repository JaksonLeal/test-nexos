package com.test.nexos.exception;

public class EmpleadoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoException() {
		super("No se pudo crear el empleado");
	}

	public EmpleadoException(String message) {
		super(message);
	}
	

}
