package com.bennibon.rpn.input;

import com.bennibon.rpn.facilitation.Facilitator;
import com.bennibon.rpn.facilitation.FacilitatorImpl;

public abstract class InputStrategy {
	
	protected Facilitator calcFacilitator;
	
	public InputStrategy() {
		calcFacilitator = new FacilitatorImpl();
	}

	public abstract void process();
}
