package com.bennibon.rpn.calc;

import java.util.Iterator;
import java.util.List;

import com.bennibon.rpn.calc.exceptions.CalculationServiceException;
import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.interfaces.CalcService;
import com.bennibon.rpn.calc.types.Operator;

/**
 * The implementation of the Calculation Service.
 * @author Ben Bonavia 2017
 */
public class CalcServiceImpl implements CalcService {
	
	/** Regular expression to match a number on. */
	private static final String NUMBER_REGEX = "\\d+(\\.\\d+)?";

	/** The input handler. */
	private InputHandler inputHandler;

	/**
	 * Constructor.
	 */
	public CalcServiceImpl() {
		inputHandler = new InputHandler();
	}

	@Override
	public void solve(List<String> input, CalcMemory memory) 
			throws CalculationServiceException {
		Operator op;
		int index = 0;
		String currentInput;
		for (Iterator<String> iterator = input.iterator(); iterator.hasNext(); index++) {
			currentInput = iterator.next();
			if (currentInput.matches(NUMBER_REGEX)) {
				inputHandler.handleNumber(Double.parseDouble(currentInput), memory);
			} else {
				// if the input is not a number, its an operator
				op = Operator.fromRepresentation(currentInput);
				validateInput(op, memory.stack().size(), index);
				inputHandler.handleOperator(op, memory);
			}
			
			System.out.println("intermediate stack " + memory.stack());
			System.out.println("intermediate history " + memory.history());
		}

	}


	/**
	 * Validate the input to ensure the operation can be successfully performed.
	 * @param op The operator
	 * @param index The index of the operator in the input list
	 * @throws InsufficientParametersException If the stack is not big enough to perform the @{Operator}
	 */
	private void validateInput(Operator op, int stackSize, int index) throws InsufficientParametersException {
		if (op.operands() > stackSize) {
			throw new InsufficientParametersException(op, originalInputPosition(index));
		}
		
	}

	/**
	 * Determine the original input position based on the index in the input list.
	 * @param index the index in the input list.
	 * @return the original input position
	 */
	private int originalInputPosition(int index) {
		return index * 2 + 1;
	}

}
