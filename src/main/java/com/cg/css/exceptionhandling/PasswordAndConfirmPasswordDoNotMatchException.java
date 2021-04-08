package com.cg.css.exceptionhandling;

public class PasswordAndConfirmPasswordDoNotMatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordAndConfirmPasswordDoNotMatchException(String msg) {
		super(msg);
	}

}
