package com.bennibon.rpn.calc.exceptions;

/**
 * Exception to be raised when an unknown operator is entered.
 * @author Ben Bonavia 2017
 */
public class UnknownOperatorException extends CalculationServiceException {
	
	/** Default serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Exception message placeholder. */
	private static final String EXCEPTION_MESSAGE = "Unknown operator provided: ";
	
	/**
	 * Constructor.
	 * @param operator The unknown operator.
	 */
	public UnknownOperatorException(String operator) {
		super(EXCEPTION_MESSAGE + operator);
	}
}
