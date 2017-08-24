package com.bennibon.rpn.facilitation;

import java.util.Queue;

import com.bennibon.rpn.calc.CalcMemoryImpl;
import com.bennibon.rpn.calc.CalcServiceImpl;
import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;
import com.bennibon.rpn.calc.interfaces.CalcMemory;
import com.bennibon.rpn.calc.interfaces.CalcService;

public class FacilitatorImpl implements Facilitator {

	private CalcService calcService;
	
	private CalcMemory memory;
	
	private InputParser parser;
	
	public FacilitatorImpl() {
		calcService = new CalcServiceImpl();
		memory = new CalcMemoryImpl();
		parser = new InputParser();
	}
	
	@Override
	public boolean facilitate(String inputString) {
		Queue<NumberOrOperator> inputQueue;
		boolean stop = false;
		try {
			inputQueue = parser.parse(inputString);
			
			calcService.solve(inputQueue, memory);
			
		} catch (UnknownOperatorException e) {
			System.err.println(e.getMessage());
		} catch (InsufficientParametersException e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println(memory.printStack());
		}
		return stop; 
	}

}
