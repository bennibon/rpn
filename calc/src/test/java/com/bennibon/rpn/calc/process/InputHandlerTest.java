package com.bennibon.rpn.calc.process;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bennibon.rpn.calc.CalcMemory;
import com.bennibon.rpn.calc.types.Operator;

/**
 * Test the input handler to make sure the correct operations are being performed.
 * @author Ben Bonavia 2017
 */
public class InputHandlerTest {

    /** Should be able to handle up to 15 decimal places. */
    private static final double DOUBLE_PRECISION = 1e-15;

    /** Input Handler being tested */
    private static InputHandler inputHandler;

    /**
     * Calculator memory being used. This is being indirectly tested for the
     * {@link CalcMemory#restorePrevious()} and {@link CalcMemory#save()} methods.
     * */
    private static CalcMemory memory;

    @BeforeClass
    public static void setUp() {
        memory = new CalcMemory();
        inputHandler = new InputHandler();
    }

    /**
     * Test the control operators (UNDO, CLEAR) to ensure that they can handle a variety of scenarios.
     */
    @Test
    public void testHandleControlOperator() {
        memory.history().clear();
        memory.stack().clear();

        // test undo empty history
        inputHandler.handleOperator(Operator.UNDO, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test undo with single nominal value
        stackToContain(10d);
        Assert.assertEquals("Expected a non-empty stack", 1, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        inputHandler.handleOperator(Operator.UNDO, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test undo with multiple nominal values
        stackToContain(10d, 55d);
        Assert.assertEquals("Expected a non-empty stack", 2, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 1, memory.history().size());

        inputHandler.handleOperator(Operator.UNDO, memory);
        Assert.assertEquals("Expected a non-empty stack", 1, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test multiple undos with values
        stackToContain(10d, 55d, 25d);
        Assert.assertEquals("Expected a non-empty stack", 3, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 2, memory.history().size());

        inputHandler.handleOperator(Operator.UNDO, memory);
        inputHandler.handleOperator(Operator.UNDO, memory);
        Assert.assertEquals("Expected a non-empty stack", 1, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test multiple undos with empty history
        stackToContain(10d, 55d);
        Assert.assertEquals("Expected a non-empty stack", 2, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 1, memory.history().size());

        inputHandler.handleOperator(Operator.UNDO, memory);
        inputHandler.handleOperator(Operator.UNDO, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test clear with nothing
        memory.history().clear();
        memory.stack().clear();

        inputHandler.handleOperator(Operator.CLEAR, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        // test clear with single nominal value
        stackToContain(10d);
        Assert.assertEquals("Expected a non-empty stack", 1, memory.stack().size());
        Assert.assertEquals("Expected empty history", 0, memory.history().size());

        inputHandler.handleOperator(Operator.CLEAR, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 1, memory.history().size());

        // test clear with multiple values
        memory.history().clear();
        stackToContain(10d, 55d);
        Assert.assertEquals("Expected a non-empty stack", 2, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 1, memory.history().size());

        inputHandler.handleOperator(Operator.CLEAR, memory);
        Assert.assertEquals("Expected an empty stack", 0, memory.stack().size());
        Assert.assertEquals("Expected non-empty history", 2, memory.history().size());

    }

    /**
     * Test that a number is handled correctly.
     */
    @Test
    public void testHandleNumber() {
        memory.history().clear();
        memory.stack().clear();
        double number;
        // test adding numbers of varying types

        // integer
        number = 5;
        inputHandler.handleNumber(number, memory);
        Assert.assertTrue("Number should be committed to memory", memory.stack().contains(number));

        // simple decimal
        number = 6.2;
        inputHandler.handleNumber(number, memory);
        Assert.assertTrue("Number should be committed to memory", memory.stack().contains(number));

        // large number
        number = 93432.432;
        inputHandler.handleNumber(number, memory);
        Assert.assertTrue("Number should be committed to memory", memory.stack().contains(number));

        // small number
        number = 0.000237;
        inputHandler.handleNumber(number, memory);
        Assert.assertTrue("Number should be committed to memory", memory.stack().contains(number));
    }

    /**
     * Test the primitive operators work as expected.
     */
    @Test
    public void testHandlePrimitiveOperator() {
        memory.history().clear();
        memory.stack().clear();

        // addition
        // integer
        stackToContain(5d, 3d);
        inputHandler.handleOperator(Operator.ADD, memory);
        Assert.assertEquals("Stack not equal to expected", 8, memory.stack().peekLast(), DOUBLE_PRECISION);

        // decimal
        stackToContain(5.4, 3d);
        inputHandler.handleOperator(Operator.ADD, memory);
        Assert.assertEquals("Stack not equal to expected", 8.4, memory.stack().peekLast(), DOUBLE_PRECISION);

        // subtraction
        // integer
        stackToContain(5d, 3d);
        inputHandler.handleOperator(Operator.SUBTRACT, memory);
        Assert.assertEquals("Stack not equal to expected", 2, memory.stack().peekLast(), DOUBLE_PRECISION);

        // decimal
        stackToContain(5.4, 3.2);
        inputHandler.handleOperator(Operator.SUBTRACT, memory);
        Assert.assertEquals("Stack not equal to expected", 2.2, memory.stack().peekLast(), DOUBLE_PRECISION);

        // multiplication
        // integer
        stackToContain(5d, 3d);
        inputHandler.handleOperator(Operator.MULTIPLY, memory);
        Assert.assertEquals("Stack not equal to expected", 15, memory.stack().peekLast(), DOUBLE_PRECISION);

        // decimal
        stackToContain(5.4, 3.2);
        inputHandler.handleOperator(Operator.MULTIPLY, memory);
        Assert.assertEquals("Stack not equal to expected", 17.28, memory.stack().peekLast(), DOUBLE_PRECISION);

        // division
        // integer
        stackToContain(6d, 3d);
        inputHandler.handleOperator(Operator.DIVIDE, memory);
        Assert.assertEquals("Stack not equal to expected", 2, memory.stack().peekLast(), DOUBLE_PRECISION);

        // decimal
        stackToContain(5d, 3d);
        inputHandler.handleOperator(Operator.DIVIDE, memory);
        Assert.assertEquals("Stack not equal to expected", 1.666666666666667, memory.stack().peekLast(),
                DOUBLE_PRECISION);

        // square root
        // integer
        stackToContain(16d);
        inputHandler.handleOperator(Operator.SQRT, memory);
        Assert.assertEquals("Stack not equal to expected", 4, memory.stack().peekLast(), DOUBLE_PRECISION);

        // decimal
        stackToContain(15d);
        inputHandler.handleOperator(Operator.SQRT, memory);
        Assert.assertEquals("Stack not equal to expected", 3.872983346207417, memory.stack().peekLast(),
                DOUBLE_PRECISION);
    }

    /**
     * ensures the stack contains a set of numbers
     * @param numbers
     */
    private void stackToContain(final Double... numbers) {
        memory.stack().clear();
        for (final double number : numbers) {
            // couples this helper to testHandleNumber
            inputHandler.handleNumber(number, memory);
        }

    }

}
