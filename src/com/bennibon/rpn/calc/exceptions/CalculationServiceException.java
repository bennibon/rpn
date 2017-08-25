package com.bennibon.rpn.calc.exceptions;

/**
 * The over-arching exception thrown by the Calculation Service.
 * @author Ben Bonavia 2017
 */
public class CalculationServiceException extends Exception {

	/** Default serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * <p>
	 * To allow child classes access to be raised with a message.
	 * @param message The message to display.
	 */
	public CalculationServiceException(String message) {
		super(message);
	}
}
