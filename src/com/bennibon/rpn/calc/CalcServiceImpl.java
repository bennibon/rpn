package com.bennibon.rpn.calc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.interfaces.CalcMemory;
import com.bennibon.rpn.calc.interfaces.CalcOperation;
import com.bennibon.rpn.calc.interfaces.CalcService;
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

	private OperationValidator validator;
	private HashMap<Operator, CalcOperation> operations;
	private int operationsCounter;

	public CalcServiceImpl() {
		operations = new HashMap<>();
		validator = new OperationValidator();
		compileOperations();
	}

	private void compileOperations() {
		operations.put(Operator.ADD, m -> {
			double slave = m.stack().pop();
			double master = m.stack().pop();
			double result = master + slave;
			m.stack().push(result);
			operationsCounter += 4;
		});
		operations.put(Operator.SUBTRACT, m -> {
			double slave = m.stack().pop();
			double master = m.stack().pop();
			double result = master - slave;
			m.stack().push(result);
			operationsCounter += 4;
		});
		operations.put(Operator.MULTIPLY, m -> {
			double slave = m.stack().pop();
			double master = m.stack().pop();
			double result = master * slave;
			m.stack().push(result);
			operationsCounter += 4;
		});
		operations.put(Operator.DIVIDE, m -> {
			double divisor = m.stack().pop();
			double dividend = m.stack().pop();
			double result = dividend / divisor;
			m.stack().push(result);
			operationsCounter += 4;
		});
		operations.put(Operator.SQRT, m -> {
			double input = m.stack().pop();
			double result = Math.sqrt(input);
			m.stack().push(result);
			operationsCounter += 3;
		});
		operations.put(Operator.UNDO, m -> {
			Collection<Double> prev = m.history().pop();
			m.stack().clear();
			m.stack().addAll(prev);
			operationsCounter ++;
		});
		operations.put(Operator.CLEAR, m -> {
			m.stack().clear();
			m.history().clear();
			operationsCounter++;
		});
	}

	@Override
	public void solve(Stack<NumberOrOperator> input, CalcMemory memory) throws InsufficientParametersException {
		NumberOrOperator currentInput;
		Operator currentOp;
		operationsCounter = 0;
		while (!input.isEmpty()) {
			currentInput = input.pop();

			if (currentInput.isOperator()) {
				currentOp = currentInput.operator();
				if (currentOp.isPrimitiveOperator()) {
					memory.save();
				}
				validator.validateArguments(currentOp, memory.stack().size());
				operations.get(currentOp).performOperation(memory);
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
