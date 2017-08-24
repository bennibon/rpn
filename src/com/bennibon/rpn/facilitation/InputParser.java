package com.bennibon.rpn.facilitation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;
import com.bennibon.rpn.calc.types.Operator;

final public class InputParser {

	private static final String NUMBER_REGEX = "\\d+(\\.\\d+)?";

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
	

	final public Queue<NumberOrOperator> parse(String inputString) throws UnknownOperatorException {
		Queue<NumberOrOperator> inputQueue = new LinkedList<>();
		List<String> inputs = Arrays.asList(inputString.toLowerCase().trim().split(INPUT_SEPARATOR));
		
		if (inputs.isEmpty())
			return inputQueue;
		
		
		for (String input : inputs) {
			if (input.matches(NUMBER_REGEX)) {
				inputQueue.add(NumberOrOperator.ofNumber(Double.parseDouble(input)));
			} else if (OPERATOR_MAP.containsKey(input)){
				inputQueue.add(NumberOrOperator.ofOperator(OPERATOR_MAP.get(input)));
			} else {
				throw new UnknownOperatorException(input);
			}
		}
		return inputQueue;
		
	}
}
