package com.bennibon.rpn.calc;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Consumer;

import com.bennibon.rpn.calc.interfaces.CalcOperation;
import com.bennibon.rpn.calc.types.Operator;

/**
 * Input Handler.
 * <p>
 * Provides methods to handle both kinds of inputs:
 * <li>Numbers
 * <li>Operators
 * @author Ben Bonavia 2017
 */
public class InputHandler {

	/** The mathematical operations. */
	private static final HashMap<Operator, CalcOperation> MATH_OPERATIONS;
	
	/** The control operations. */
	private static final HashMap<Operator, Consumer<CalcMemory>> CONTROL_OPERATIONS;
	
	static {
		MATH_OPERATIONS = new HashMap<>();
		MATH_OPERATIONS.put(Operator.ADD, (a,b) -> a + b);
		MATH_OPERATIONS.put(Operator.SUBTRACT, (a,b) -> a - b);
		MATH_OPERATIONS.put(Operator.MULTIPLY, (a,b) -> a * b);
		MATH_OPERATIONS.put(Operator.DIVIDE, (a,b) -> a / b);
		MATH_OPERATIONS.put(Operator.SQRT, (a,b) -> Math.sqrt(a));
		
		CONTROL_OPERATIONS = new HashMap<>();
		CONTROL_OPERATIONS.put(Operator.UNDO, m -> m.stack().addAll(m.history().pop()));
		CONTROL_OPERATIONS.put(Operator.CLEAR, m -> m.history().clear());
	}

	/**
	 * Performs a mathematical operation on the stack.
	 * @param op The operator
	 * @param stack The stack
	 */
	private void performMathOperation(Operator op, Stack<Double> stack) {
		Double slave = null;
		Double master = null;
		if (op.isPrimitiveOperator()) {
			slave = stack.pop();
		}
		master = stack.pop();
		stack.push(MATH_OPERATIONS.get(op).performOperation(master, slave));
	}
	
	/**
	 * Performs a control operation on the {@link CalcMemory}.
	 * @param op The operator
	 * @param memory The calculator memory
	 */
	private void performControlOperation(Operator op, CalcMemory memory) {
		// clear the stack for all control operations
		memory.stack().clear();
		CONTROL_OPERATIONS.get(op).accept(memory);
	}
	
	/**
	 * Handles an {@link Operator} and its effect on the provided {@link CalcMemory}.
	 * @param op The operator to handle
	 * @param memory The Calculator Memory
	 */
	public void handleOperator(Operator op, CalcMemory memory) {
		if (op.isControlOperator()) {
			performControlOperation(op, memory);
		} else {
			memory.save();
			performMathOperation(op, memory.stack());
		}
	}
	
	/**
	 * Handles a number and its effect on the provided {@link CalcMemory}.
	 * @param number The number to handle
	 * @param memory The Calculator Memory
	 */
	public void handleNumber(Double number, CalcMemory memory) {
		memory.save();
		memory.stack().push(number);
	}

}
