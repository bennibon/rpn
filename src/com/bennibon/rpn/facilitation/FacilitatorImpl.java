package com.bennibon.rpn.facilitation;

import java.util.Arrays;
import java.util.List;

import com.bennibon.rpn.calc.CalcMemory;
import com.bennibon.rpn.calc.CalcServiceImpl;
import com.bennibon.rpn.calc.exceptions.CalculationServiceException;
import com.bennibon.rpn.calc.interfaces.CalcService;

/**
 * Implementation of the Calculator Facilitator.
 * <p>
 * This layer houses the data which is used to solve for the given input
 * as well as the history of what has been operated upon.
 * @author Ben Bonavia 2017
 */
public class FacilitatorImpl implements Facilitator {
	
	/** The separator between inputs. */
	private static final String INPUT_SEPARATOR = " ";

	/** The Calculation Service. */
	private CalcService calcService;
	
	/** The Calculator Memory. */
	private CalcMemory memory;
	
	/**
	 * Constructor.
	 */
	public FacilitatorImpl() {
		calcService = new CalcServiceImpl();
		memory = new CalcMemory();
	}
	
	@Override
	public void facilitate(String inputString) {
		try {
			calcService.solve(separateInputs(inputString), memory);
			
		} catch (CalculationServiceException e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println(memory.printStack());
		}
	}

	/**
	 * Separate the inputs so that it can be provided to {@link CalcService}.
	 * @param inputString The raw input string
	 * @return A list of inputs
	 */
	private List<String> separateInputs(String inputString) {
		return Arrays.asList(inputString.toLowerCase().trim().split(INPUT_SEPARATOR));
	}

}
