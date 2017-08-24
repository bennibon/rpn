package com.bennibon.rpn.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;
import com.bennibon.rpn.calc.types.Operator;
import com.bennibon.rpn.facilitation.NumberOrOperator;

final public class InputParser {

	private static final String INPUT_SEPARATOR = " ";
	
	private static final HashMap<String, Operator> OPERATOR_MAP = new HashMap<>();
	
	{
		OPERATOR_MAP.put("+", Operator.ADD);
		OPERATOR_MAP.put("-", Operator.SUBTRACT);
		OPERATOR_MAP.put("*", Operator.MULTIPLY);
		OPERATOR_MAP.put("/", Operator.DIVIDE);
		OPERATOR_MAP.put("sqrt", Operator.SQRT);
		OPERATOR_MAP.put("undo", Operator.UNDO);
		OPERATOR_MAP.put("clear", Operator.CLEAR);
		OPERATOR_MAP.put("quit", Operator.QUIT);
	}
	

	final public Stack<NumberOrOperator> parse(String inputString) throws UnknownOperatorException {
		Stack<NumberOrOperator> stack = new Stack<>();
		List<String> inputs = Arrays.asList(inputString.toLowerCase().trim().split(INPUT_SEPARATOR));
		
		if (inputs.isEmpty())
			return stack;
		
		// reverse inputs so they can be worked on in the correct order
		Collections.reverse(inputs);
		
		for (String input : inputs) {
			if (input.matches("\\d+(\\.\\d+)?")) {
				stack.push(NumberOrOperator.ofNumber(Double.parseDouble(input)));
			} else if (OPERATOR_MAP.containsKey(input)){
				stack.push(NumberOrOperator.ofOperator(OPERATOR_MAP.get(input)));
			} else {
				throw new UnknownOperatorException(input);
			}
			System.out.println("pushing " + stack.peek());
		}
		return stack;
		
	}
}
