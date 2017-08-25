package com.bennibon.rpn.facilitation;

/**
 * The Calculator Facilitator.
 * <p>
 * The facilitation layer provides a means to provide input to the 
 * calculation service consistently regardless of the input strategy.
 *
 * @author Ben Bonavia 2017
 */
public interface Facilitator {
	
	/**
	 * Facilitate the execution of the calculation service.
	 * @param inputString The string of inputs to solve.
	 */
	void facilitate(String inputString);
}
