package com.bennibon.rpn.calc.interfaces;

import com.bennibon.rpn.calc.types.OneOrTwo;

@FunctionalInterface
public interface CalcOperation {

	Double performOperation(OneOrTwo operand);
}
