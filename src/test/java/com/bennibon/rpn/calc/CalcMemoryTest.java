package com.bennibon.rpn.calc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the Calculator Memory.
 * <p>
 * This test specifically tests the {@link CalcMemory#printStack()} method.
 * @author Ben Bonavia 2017
 */
public class CalcMemoryTest {

    /** The Calculator Memory being tested. */
    private static CalcMemory memory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        memory = new CalcMemory();
    }

    /**
     * Test that the stack is printed correctly to up to 10 decimal places even though the calculator can handle a lot more.
     */
    @Test
    public void testPrintStack() {
        // give the stack a number with no decimal places
        checkPrintedNumberLength(5, 1);

        // give the stack a number with 5 decimal places
        checkPrintedNumberLength(6.12345, 7);

        // give the stack a number with 10 decimal places
        checkPrintedNumberLength(6.0123456789, 12);

        // give the stack a number with 15 decimal places
        checkPrintedNumberLength(6.012345678912345, 12);
    }

    /**
     * Check the printed stack number length against the expected length.
     * @param number the number to check
     * @param expectedLength the expected length that should be displayed
     */
    private void checkPrintedNumberLength(final double number, final int expectedLength) {
        String stackOutput;
        String[] splitOutput;
        // clear the stack to make sure there is only one number to look at
        memory.stack().clear();
        memory.stack().addLast(number);
        stackOutput = memory.printStack();

        // remove the "stack: " part by splitting by space and examining the
        // last element
        splitOutput = stackOutput.split(" ");
        Assert.assertEquals("Length of number not as expected", expectedLength,
                splitOutput[splitOutput.length - 1].length());
    }

}
