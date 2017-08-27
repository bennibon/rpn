package com.bennibon.rpn.calc;

import java.util.Iterator;
import java.util.List;

import com.bennibon.rpn.calc.exceptions.CalculationServiceException;
import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;
import com.bennibon.rpn.calc.process.InputHandler;
import com.bennibon.rpn.calc.types.Operator;

/**
 * The Calculation Service.
 * <p>
 * Provides a solving service for the RPN Calculator.
 * 
 * @author Ben Bonavia 2017
 */
public class CalcService {

    /** Regular expression to match a number on. */
    private static final String NUMBER_REGEX = "\\d+(\\.\\d+)?";

    /** The input handler. */
    private final InputHandler inputHandler;

    /**
     * Constructor.
     */
    CalcService() {
        inputHandler = new InputHandler();
    }

    /**
     * Store the input to the calculator memory and reduce the stack.
     * 
     * @param input The input for to commit to memory and solve
     * @param memory The calculator memory
     * @throws CalculationServiceException Thrown when calculation based on the
     * input cannot be completed in its entirety.
     */
    public void solve(final List<String> input, final CalcMemory memory) throws CalculationServiceException {
        Operator op;
        int index = 0;
        String currentInput;
        for (final Iterator<String> iterator = input.iterator(); iterator.hasNext(); index++) {
            currentInput = iterator.next();
            if (currentInput.matches(NUMBER_REGEX)) {
                inputHandler.handleNumber(Double.parseDouble(currentInput), memory);
            } else {
                // if the input is not a number, its an operator
                op = Operator.fromRepresentation(currentInput);
                validateInput(op, memory.stack().size(), index);
                inputHandler.handleOperator(op, memory);
            }
        }

    }

    /**
     * Validate the input to ensure the operation can be successfully performed.
     * 
     * @param op The operator
     * @param index The index of the operator in the input list
     * @throws InsufficientParametersException If the stack is not big enough to
     * perform the @{Operator}
     */
    private void validateInput(final Operator op, final int stackSize, final int index)
            throws InsufficientParametersException {
        if (op.operands() > stackSize) {
            // multiply index by 2 to take into account whitespace, add 1 to fix
            // up the start offset.
            final int originalPosition = index * 2 + 1;
            throw new InsufficientParametersException(op, originalPosition);
        }

    }

}
