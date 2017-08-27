package com.bennibon.rpn.calc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bennibon.rpn.calc.CalcMemory;
import com.bennibon.rpn.calc.CalcService;
import com.bennibon.rpn.calc.exceptions.CalculationServiceException;
import com.bennibon.rpn.calc.exceptions.InsufficientParametersException;

/**
 * Test the Calculation Service to ensure the correct validation of input and
 * correct contents of the stack.
 * <p>
 * To do this, the supplied examples will be tested.
 * 
 * @author Ben Bonavia 2017
 *
 */
public class CalcServiceTest {

    /** The Calculation Service to test. */
    private static CalcService service;

    /** The memory used to test and validate. */
    private static CalcMemory memory;

    /** Input string to used for the test. */
    private static List<String> input;

    /** What the expected stack should look like for each test. */
    private static Deque<Double> expectedStack;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new CalcService();
        memory = new CalcMemory();
        input = new ArrayList<>();
        expectedStack = new ArrayDeque<>();
    }

    @Before
    public void beforeTest() {
        memory.stack().clear();
        memory.history().clear();
        input.clear();
        expectedStack.clear();
    }

    /**
     * Go through the expected and actual output stacks to determine if they're
     * equal.
     * 
     * @param expected The expected result
     * @param actual The actual result
     * @return If the actual result reflects the expected
     */
    private boolean stacksAreEqual(final Deque<Double> expected, final Deque<Double> actual) {
        final Deque<Double> testedActual = new ArrayDeque<>(actual);
        while (!expected.isEmpty()) {
            if (expected.pop().doubleValue() != testedActual.pop().doubleValue()) {
                return false;
            }
        }
        return testedActual.isEmpty();
    }

    /**
     * Test the 1st provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample1() throws CalculationServiceException {
        input.add("5");
        input.add("2");

        expectedStack.add(5d);
        expectedStack.add(2d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 2nd provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample2() throws CalculationServiceException {
        input.add("2");
        input.add("sqrt");

        expectedStack.add(1.4142135623730951);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("clear");
        input.add("9");
        input.add("sqrt");

        expectedStack.add(3d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 3rd provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample3() throws CalculationServiceException {
        input.add("5");
        input.add("2");
        input.add("-");

        expectedStack.add(3d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("3");
        input.add("-");

        expectedStack.add(0d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("clear");

        // expected stack should be empty

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 4th provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample4() throws CalculationServiceException {
        input.add("5");
        input.add("4");
        input.add("3");
        input.add("2");

        expectedStack.add(5d);
        expectedStack.add(4d);
        expectedStack.add(3d);
        expectedStack.add(2d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("undo");
        input.add("undo");
        input.add("*");

        expectedStack.add(20d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("5");
        input.add("*");

        expectedStack.add(100d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("undo");

        expectedStack.add(20d);
        expectedStack.add(5d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 5th provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample5() throws CalculationServiceException {
        input.add("7");
        input.add("12");
        input.add("2");
        input.add("/");

        expectedStack.add(7d);
        expectedStack.add(6d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("*");

        expectedStack.add(42d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("4");
        input.add("/");

        expectedStack.add(10.5);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 6th provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample6() throws CalculationServiceException {
        input.add("1");
        input.add("2");
        input.add("3");
        input.add("4");
        input.add("5");

        expectedStack.add(1d);
        expectedStack.add(2d);
        expectedStack.add(3d);
        expectedStack.add(4d);
        expectedStack.add(5d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("*");

        expectedStack.add(1d);
        expectedStack.add(2d);
        expectedStack.add(3d);
        expectedStack.add(20d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("clear");
        input.add("3");
        input.add("4");
        input.add("-");

        expectedStack.add(-1d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 7th provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample7() throws CalculationServiceException {
        input.add("1");
        input.add("2");
        input.add("3");
        input.add("4");
        input.add("5");

        expectedStack.add(1d);
        expectedStack.add(2d);
        expectedStack.add(3d);
        expectedStack.add(4d);
        expectedStack.add(5d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));

        input.clear();
        input.add("*");
        input.add("*");
        input.add("*");
        input.add("*");

        expectedStack.add(120d);

        service.solve(input, memory);
        Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
    }

    /**
     * Test the 8th provided example.
     * 
     * @throws CalculationServiceException Should not be thrown.
     */
    @Test
    public void testExample8() throws CalculationServiceException {
        input.add("1");
        input.add("2");
        input.add("3");
        input.add("*");
        input.add("5");
        input.add("+");
        input.add("*");
        input.add("*");
        input.add("6");
        input.add("5");

        expectedStack.add(11d);

        try {
            service.solve(input, memory);
        } catch (final InsufficientParametersException ipe) {
            Assert.assertEquals("Exception message not correct", "operator * (position: 15): insufficient parameters",
                    ipe.getMessage());
            Assert.assertTrue("Stack contents are incorrect", stacksAreEqual(expectedStack, memory.stack()));
        }
    }

}
