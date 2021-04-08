package com.cg.css.exceptionhandling;

public class InvalidCardNumberException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCardNumberException(String msg) {
		super(msg);
	}

}
