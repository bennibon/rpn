package com.bennibon.rpn.calc.exceptions;

import com.bennibon.rpn.calc.types.Operator;

/**
 * Exception to be raised when there are insufficient parameters for an
 * operation.
 * 
 * @author Ben Bonavia 2017
 */
public class InsufficientParametersException extends CalculationServiceException {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Exception placeholder message. */
    private static final String EXCEPTION_MESSAGE = "operator %s (position: %d): insufficient parameters";

    /**
     * Constructor.
     * 
     * @param operator The operator which has insufficient parameters.
     * @param position Its position in the input string.
     */
    public InsufficientParametersException(final Operator operator, final int position) {
        super(String.format(EXCEPTION_MESSAGE, operator.representation(), position));
    }
}
