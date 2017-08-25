package com.bennibon.rpn.calc.interfaces;

import java.util.List;

import com.bennibon.rpn.calc.CalcMemory;
import com.bennibon.rpn.calc.exceptions.CalculationServiceException;

/**
 * The Calculation Service. 
 * <p>
 * Provides a solving service for the RPN Calculator. 
 * @author Ben Bonavia 2017
 */
public interface CalcService {

	/**
	 * Store the input to the calculator memory and reduce the stack.
	 * @param input The input for to commit to memory and solve
	 * @param memory The calculator memory
	 * @throws CalculationServiceException Thrown when calculation based on the input 
	 * 			cannot be completed in its entirety.
	 */
	void solve(List<String> input, CalcMemory memory) throws CalculationServiceException;
}
