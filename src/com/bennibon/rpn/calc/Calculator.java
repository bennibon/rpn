package com.bennibon.rpn.calc;

/**
 * The Calculator Interface.
 * <p>
 * This interface provides a means to provide input to the
 * calculation service consistently regardless of the input strategy.
 *
 * @author Ben Bonavia 2017
 */
public interface Calculator {

    /**
     * @return a new Calculator.
     */
    public static Calculator newCalc() {
        return new CalcFacade();
    }

    /**
     * Process the input.
     * @param inputString The string of inputs to solve.
     */
    void process(String inputString);
}
