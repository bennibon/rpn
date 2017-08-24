package com.bennibon.rpn.calc.exceptions;

public class UnknownOperatorException extends Exception {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "Unknown operator provided: ";
	
	public UnknownOperatorException(String item) {
		super(EXCEPTION_MESSAGE + item);
	}
}
