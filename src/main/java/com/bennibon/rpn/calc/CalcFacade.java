package com.bennibon.rpn.calc;

import java.util.Arrays;
import java.util.List;

import com.bennibon.rpn.calc.exceptions.CalculationServiceException;

/**
 * The Calculator Facade.
 * <p>
 * This stores the data which is used to solve for the given input
 * as well as the history of what has been operated upon. This way,
 * the Calculation Service does not need store data and only needs
 * to process input.
 * @author Ben Bonavia 2017
 */
public class CalcFacade implements Calculator {

    /** The separator between inputs. */
    private static final String INPUT_SEPARATOR = " ";

    /** The Calculation Service. */
    private final CalcService calcService;

    /** The Calculator Memory. */
    private final CalcMemory memory;

    /**
     * Package private constructor.
     */
    CalcFacade() {
        calcService = new CalcService();
        memory = new CalcMemory();
    }

    @Override
    final public void process(final String inputString) {
        try {
            calcService.solve(separateInputs(inputString), memory);

        } catch (final CalculationServiceException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println(memory.printStack());
        }
    }

    /**
     * Separate the inputs so that it can be provided to {@link CalcService}.
     * @param inputString The raw input string
     * @return A list of inputs
     */
    final private List<String> separateInputs(final String inputString) {
        return Arrays.asList(inputString.trim().split(INPUT_SEPARATOR));
    }

}
