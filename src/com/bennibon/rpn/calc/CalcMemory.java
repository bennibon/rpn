package com.bennibon.rpn.calc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *  * The memory of the calculator.
 * <p>
 * The memory consists of:
 * <li> The current stack which is to operated on
 * <li> The history of previous stack states
 * @author Ben Bonavia 2017
 */
public class CalcMemory {
	
	/** The current stack to be operated upon. */
	private Stack<Double> stack;
	
	/** The history of previous stacks. */
	private Stack<Collection<Double>> history;
	
	/**
	 * Constructor.
	 */
	public CalcMemory() {
		stack = new Stack<>();
		history = new Stack<>();
	}
 
	/**
	 * Save the current stack state to history.
	 */
	public void save() {
		history.push((Collection<Double>) stack.clone());
	}

	/**
	 * @return The history of previous stack states.
	 */
	public Stack<Collection<Double>> history() {
		return history;
	}

	/**
	 * @return The current stack.
	 */
	public Stack<Double> stack() {
		return stack;
	}

	/**
	 * @return The stack printed as a {@link String}.
	 */
	public String printStack() {
		DecimalFormat formatter = new DecimalFormat("#.##########");
		StringBuilder stackSb = new StringBuilder("stack: ");
		List<Double> outputList = new ArrayList<>(stack);
		stackSb.append(outputList.stream().map(d -> formatter.format(d.doubleValue())).collect(Collectors.joining(" ")));
		return stackSb.toString();
	}

}
