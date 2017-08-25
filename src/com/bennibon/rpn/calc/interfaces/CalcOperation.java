package com.bennibon.rpn.calc.interfaces;

/**
 * Functional interface to perform a calculation operation.
 * @author Ben Bonavia 2017
 */
@FunctionalInterface
public interface CalcOperation {
	
	/**
	 * Perform an operation for the given operands and return its result.
	 * @param operand1 The first operand
	 * @param operand2 The second operand
	 * @return The result of the operation
	 */
	Double performOperation(Double operand1, Double operand2);
}
