package com.bennibon.rpn.input;

import com.bennibon.rpn.calc.Calculator;

/**
 * The strategy for acquiring input for the RPN Calculator
 * 
 * @author Ben Bonavia 2017
 */
public abstract class InputStrategy {

	/** The banner to display when starting to receive. */
	final protected static String BANNER = "Reverse Polish Notation Calculator\n2017 Ben Bonavia\n";

	/** The prompt to display with text input. */
	final protected static String PROMPT = "> ";

	/** Access to the calculator. **/
	protected Calculator calc;

	/**
	 * Constructor.
	 */
	public InputStrategy() {
		calc = Calculator.newCalc();
	}

	/**
	 * Execute the given input strategy.
	 */
	public abstract void execute();
}
