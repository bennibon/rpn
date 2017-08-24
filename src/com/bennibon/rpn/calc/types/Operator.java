package com.bennibon.rpn.calc.types;

public enum Operator {
	// Primitive operators
	ADD ("+"),
	SUBTRACT ("-"),
	MULTIPLY ("*"),
	DIVIDE ("/"),
	
	// Non-primitive
	SQRT ("sqrt"),
	
	// control operators
	UNDO ("undo"),
	CLEAR ("clear"),
	QUIT ("quit");
	
	private String item;
	
	Operator(String input) {
		this.item = input;
	}
	
	public String toString() {
		return item;
	}
	
	public boolean isPrimitiveOperator() {
		return this.compareTo(DIVIDE) <= 0;
	}
}
