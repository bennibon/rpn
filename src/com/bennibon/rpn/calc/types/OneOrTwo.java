package com.bennibon.rpn.calc.types;

import java.util.Optional;

public class OneOrTwo {
	private Optional<Double> first;
	private Optional<Double> second;

	private OneOrTwo() {
		//nothing is set
	}
	
	private OneOrTwo(Double single) {
		first = Optional.of(single);
	}
	
	private OneOrTwo(Double f, Double s){
		first = Optional.of(f);
		second = Optional.of(s);
	}
	
	public static OneOrTwo one(Double single) {
		return new OneOrTwo(single);
	}
	
	public static OneOrTwo two(Double first, Double second) {
		return new OneOrTwo(first, second);
	}
	
	public double first() {
		return first.get();
	}
	
	public double second() {
		return second.orElseGet(() -> first());
	}
	
	public boolean isTwo() {
		return second.isPresent();
	}
}
