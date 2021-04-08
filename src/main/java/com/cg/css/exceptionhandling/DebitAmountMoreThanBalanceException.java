package com.cg.css.exceptionhandling;

public class DebitAmountMoreThanBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DebitAmountMoreThanBalanceException(String msg) {
		super(msg);
	}

}
