package com.bennibon.rpn.calc.interfaces;

import java.util.Collection;
import java.util.Stack;

/**
 * The memory of the calculator.
 */
public interface CalcMemory {

	void save();
	
	Stack<Collection<Double>> history();
	
	Stack<Double> stack();
	
	String printStack();
}
