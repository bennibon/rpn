package com.bennibon.rpn.calc.exceptions;

import com.bennibon.rpn.calc.types.Operator;

public class InsufficientParametersException extends Exception {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "operator %s (position: %d): insufficient parameters ";
	
	public InsufficientParametersException(Operator operator, int position) {
		super(String.format(EXCEPTION_MESSAGE, operator.toString(), position));
	}
}
