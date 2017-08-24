package com.bennibon.rpn.calc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import com.bennibon.rpn.calc.interfaces.CalcMemory;

public class CalcMemoryImpl implements CalcMemory {
	
	private Stack<Double> stack;
	
	private Stack<Collection<Double>> history;
	
	public CalcMemoryImpl() {
		stack = new Stack<>();
		history = new Stack<>();
	}
 
	@Override
	public void save() {
		history.push((Collection<Double>) stack.clone());
	}

	@Override
	public Stack<Collection<Double>> history() {
		return history;
	}

	@Override
	public Stack<Double> stack() {
		return stack;
	}

	@Override
	public String printStack() {
		DecimalFormat formatter = new DecimalFormat("#.##########");
		StringBuilder stackSb = new StringBuilder("stack: ");
		List<Double> outputList = new ArrayList<>(stack);
		stackSb.append(outputList.stream().map(d -> formatter.format(d.doubleValue())).collect(Collectors.joining(" ")));
		return stackSb.toString();
	}

}
