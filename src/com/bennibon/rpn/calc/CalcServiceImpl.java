package com.bennibon.rpn.calc;

import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.interfaces.CalcMemory;
import com.bennibon.rpn.calc.interfaces.CalcOperation;
import com.bennibon.rpn.calc.interfaces.CalcService;
import com.bennibon.rpn.calc.types.OneOrTwo;
import com.bennibon.rpn.calc.types.Operator;
import com.bennibon.rpn.facilitation.NumberOrOperator;

public class CalcServiceImpl implements CalcService {
	
	public class OperationValidator {
		private final HashMap<Operator, Integer> ARGS_REQUIRED = new HashMap<>();
		
		{
			ARGS_REQUIRED.put(Operator.ADD, 2);
			ARGS_REQUIRED.put(Operator.SUBTRACT,2);
			ARGS_REQUIRED.put(Operator.MULTIPLY,2);
			ARGS_REQUIRED.put(Operator.DIVIDE,2);
			ARGS_REQUIRED.put(Operator.SQRT,1);
			ARGS_REQUIRED.put(Operator.UNDO,0);
			ARGS_REQUIRED.put(Operator.CLEAR,0);
			ARGS_REQUIRED.put(Operator.QUIT,0);
		}
		
		
		public void validateArguments(Operator op, int stackSize) throws InsufficientParametersException {
			if (ARGS_REQUIRED.get(op) > stackSize) {
				throw new InsufficientParametersException(op, operationsCounter);
			}
		}
		
		public boolean performsOnArguments(Operator op) {
			return ARGS_REQUIRED.get(op) > 0;
		}
	}

	public class OperationPerformer {
		
		private HashMap<Operator, CalcOperation> operations;
		private HashMap<Operator, Consumer<CalcMemory>> controlOperations;
		
		public OperationPerformer() {
			operations = new HashMap<>();
			controlOperations = new HashMap<>();
			compileOperations();
		}
		
		private void compileOperations() {
			operations.put(Operator.ADD, n -> n.first() + n.second());
			operations.put(Operator.SUBTRACT, n -> n.first() - n.second());
			operations.put(Operator.MULTIPLY, n -> n.first() * n.second());
			operations.put(Operator.DIVIDE, n -> n.first() / n.second());
			operations.put(Operator.SQRT, n -> Math.sqrt(n.first()));
			
			controlOperations.put(Operator.UNDO, m -> m.stack().addAll(m.history().pop()));
			controlOperations.put(Operator.CLEAR, m -> m.history().clear());
		}

		private void performMathOperation(Operator op, Stack<Double> stack) {
			final OneOrTwo operand;
			if (op.isPrimitiveOperator()) {
				double slave = stack.pop();
				double master = stack.pop();
				operand = OneOrTwo.two(master, slave);
				operationsCounter += 2;
			} else {
				operand = OneOrTwo.one(stack.pop());
				operationsCounter++;
			}
			stack.push(operations.get(op).performOperation(operand));
			operationsCounter += 2;			
		}
		
		private void performControlOperation(Operator op, CalcMemory memory) {
			// clear the stack for all control operations
			memory.stack().clear();
			operationsCounter++;
			controlOperations.get(op).accept(memory);
		}
		
		public void performOperation(Operator op, CalcMemory memory) {
			if (op.isControlOperator()) {
				performControlOperation(op, memory);
			} else {
				memory.save();
				performMathOperation(op, memory.stack());
			}
		}
		
	}
	
	private OperationValidator validator;
	private OperationPerformer performer;
	private int operationsCounter;

	public CalcServiceImpl() {
		validator = new OperationValidator();
		performer = new OperationPerformer();
	}

	@Override
	public void solve(Queue<NumberOrOperator> input, CalcMemory memory) 
			throws InsufficientParametersException {
		NumberOrOperator currentInput;
		Operator currentOp;
		operationsCounter = 0;
		while (!input.isEmpty()) {
			currentInput = input.remove();

			if (currentInput.isOperator()) {
				currentOp = currentInput.operator();
				validator.validateArguments(currentOp, memory.stack().size());
				performer.performOperation(currentOp, memory);
			}
			
			if (currentInput.isNumber()) {
				memory.save();
				memory.stack().push(currentInput.number());
				operationsCounter++;
			}
			
			System.out.println("intermediate stack " + memory.stack());
			System.out.println("intermediate history " + memory.history());
			System.out.println("operationsCounter: " + operationsCounter);
		}

	}

}
