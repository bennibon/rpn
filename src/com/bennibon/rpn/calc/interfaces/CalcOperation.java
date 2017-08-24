package com.bennibon.rpn.calc.interfaces;

@FunctionalInterface
public interface CalcOperation {

	void performOperation(CalcMemory memory);
}
