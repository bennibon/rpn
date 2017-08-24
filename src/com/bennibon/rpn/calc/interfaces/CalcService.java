package com.bennibon.rpn.calc.interfaces;

import java.util.Queue;

import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.facilitation.NumberOrOperator;

/**
 * The Calculator Service. 
 * 
 * Provides a solver.
 */
public interface CalcService {

	/**
	 * Solve for the input applying it to the calculator memory
	 * @param input
	 * @param memory
	 * @throws InsufficientParametersException 
	 */
	void solve(Queue<NumberOrOperator> input, CalcMemory memory) throws InsufficientParametersException;
}
