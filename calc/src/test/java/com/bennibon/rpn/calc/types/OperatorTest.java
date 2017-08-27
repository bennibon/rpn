package com.bennibon.rpn.calc.types;

import org.junit.Assert;
import org.junit.Test;

import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;

/**
 * Test that the operators can be parsed correctly.
 * @author Ben Bonavia 2017
 */
public class OperatorTest {

    /**
     * Test that the input can be parsed to an Operator.
     * @throws UnknownOperatorException If the operation fails, this should not be thrown.
     */
    @Test
    public void fromRepresentationTestExpectedValues() throws UnknownOperatorException {
        // Arithmetic operators
        Operator result;
        String input;

        // Add
        input = "+";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.ADD, result);

        // Subtract
        input = "-";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.SUBTRACT, result);

        // Multiply
        input = "*";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.MULTIPLY, result);

        // Divide
        input = "/";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.DIVIDE, result);

        // Square Root
        // Lower case
        input = "sqrt";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.SQRT, result);

        // Upper case
        input = "SQRT";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.SQRT, result);

        // Control Operators

        // Undo
        // Lower case
        input = "undo";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.UNDO, result);

        // Upper case
        input = "UNDO";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.UNDO, result);

        // Clear
        // Lower case
        input = "clear";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.CLEAR, result);

        // Upper case
        input = "CLEAR";
        result = Operator.fromRepresentation(input);
        Assert.assertEquals("Operator is not what is expected", Operator.CLEAR, result);

    }

    /**
     * Tests that when an unknown value is present, the correct exception is thrown
     * @throws UnknownOperatorException Expected to be thrown.
     */
    @Test(expected = UnknownOperatorException.class)
    public void fromRepresentationTestUnknownValue() throws UnknownOperatorException {
        Operator.fromRepresentation("unknown");
    }

}
