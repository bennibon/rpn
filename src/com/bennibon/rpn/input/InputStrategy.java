package com.bennibon.rpn.input;

import com.bennibon.rpn.facilitation.Facilitator;
import com.bennibon.rpn.facilitation.FacilitatorImpl;

public abstract class InputStrategy {
	
	final private static String BANNER = "Reverse Polish Notation Calculator\n2017 Ben Bonavia\n";
	
	final protected static String PROMPT = "> ";
	
	protected Facilitator calcFacilitator;
	
	public InputStrategy() {
		calcFacilitator = new FacilitatorImpl();
	}
	
	protected void displayBanner() {
		System.out.println(BANNER);
	}

	public abstract void process();
}
