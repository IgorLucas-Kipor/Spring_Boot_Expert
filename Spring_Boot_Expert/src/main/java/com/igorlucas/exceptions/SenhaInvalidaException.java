package com.igorlucas.exceptions;

public class SenhaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SenhaInvalidaException() {
		super("Senha inválida.");
	}

}
