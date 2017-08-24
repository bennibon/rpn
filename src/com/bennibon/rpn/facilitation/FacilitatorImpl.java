package com.bennibon.rpn.facilitation;

import java.util.Stack;

import com.bennibon.rpn.calc.CalcMemoryImpl;
import com.bennibon.rpn.calc.CalcServiceImpl;
import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;
import com.bennibon.rpn.calc.interfaces.CalcMemory;
import com.bennibon.rpn.calc.interfaces.CalcService;
import com.bennibon.rpn.input.InputParser;

public class FacilitatorImpl implements Facilitator {

	private CalcService service;
	
	private CalcMemory memory;
	
	private InputParser parser;
	
	public FacilitatorImpl() {
		service = new CalcServiceImpl();
		memory = new CalcMemoryImpl();
		parser = new InputParser();
	}
	
	@Override
	public boolean facilitate(String inputString) {
		Stack<NumberOrOperator> inputStack;
		boolean stop = false;
		try {
			inputStack = parser.parse(inputString);
//			System.out.println(inputStack.toString());
			
			service.solve(inputStack, memory);
			
		} catch (UnknownOperatorException e) {
			System.err.println(e.getMessage());
			
		} catch (InsufficientParametersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println(memory.printStack());
		}
		return stop; 
	}

}
