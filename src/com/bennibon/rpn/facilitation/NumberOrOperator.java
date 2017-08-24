package com.bennibon.rpn.facilitation;

import java.util.Optional;

import com.bennibon.rpn.calc.types.Operator;

public class NumberOrOperator {

	private Optional<Double> number;
	
	private Optional<Operator> operator;
	
	private NumberOrOperator(Optional<Double> num, Optional<Operator> op) {
		number = num;
		operator = op;
	}
	
	public static NumberOrOperator ofNumber(Double num) {
		return new NumberOrOperator(Optional.of(num), Optional.empty());
	}
	
	public static NumberOrOperator ofOperator(Operator op) {
		return new NumberOrOperator(Optional.empty(), Optional.of(op));
	}
	
	
	public boolean isNumber() {
		return number.isPresent();
	}
	
	public boolean isOperator() {
		return operator.isPresent();
	}
	
	public Double number() {
		return number.get();
	}
	
	public Operator operator() {
		return operator.get();
	}
	
	public String printContents() {
		return "NumberOrOperator=[number= " + number.toString() + " operator=" + operator.toString() + "]";
	}

	@Override
	public String toString() {
		if (number.isPresent()) {
			return number.get().toString();
		} else if (operator.isPresent()) {
			return operator.get().toString();
		} else {
			// something doesn't seem right
			return printContents();
		}

	}
}

