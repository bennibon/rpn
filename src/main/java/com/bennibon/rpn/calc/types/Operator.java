package com.bennibon.rpn.calc.types;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bennibon.rpn.calc.exceptions.UnknownOperatorException;

/**
 * Operations which can be performed by the RPN Calculator.
 * <p>
 * Each operator has the following attributes
 * <li> Representation: How it is provided as the input
 * <li> Operands: How many operands it requires
 * @author Ben Bonavia 2017
 */
public enum Operator {
    // Primitive operators
    ADD("+", 2), SUBTRACT("-", 2), MULTIPLY("*", 2), DIVIDE("/", 2),

    // Non-primitive
    SQRT("sqrt", 1),

    // control operators
    UNDO("undo", 0), CLEAR("clear", 0);

    /** The string representation of the operator. */
    private String rep;

    /** The number of operands which are operated on. */
    private int operands;

    /**
     * Constructor.
     * @param _rep The string representation of the operator
     * @param _operands The number of operands to be operated on
     */
    private Operator(final String _rep, final int _operands) {
        this.rep = _rep;
        this.operands = _operands;
    }

    /**
     * Determine an {@link Operator} from a representation string.
     * @param rep the representation string
     * @return the operator
     * @throws UnknownOperatorException if an operator does not exist for the representation
     */
    public static Operator fromRepresentation(final String rep) throws UnknownOperatorException {
        return Arrays.asList(values()).stream().filter(r -> r.representation().equalsIgnoreCase(rep)).findFirst()
                .orElseThrow(() -> new UnknownOperatorException(rep));
    }

    /**
     * @return A list of operator values as strings.
     */
    public static List<String> valuesAsStrings() {
        return Arrays.asList(values()).stream().map(o -> o.representation()).collect(Collectors.toList());
    }

    /**
     * @return If this operator is a control operator.
     */
    public boolean isControlOperator() {
        return this.compareTo(UNDO) >= 0;
    }

    /**
     * @return If this operator is a primitive operator.
     */
    public boolean isPrimitiveOperator() {
        return this.compareTo(DIVIDE) <= 0;
    }

    /**
     * @return the number of operands which are operated on.
     */
    public int operands() {
        return operands;
    }

    /**
     * @return the operator to its text representation.
     */
    public String representation() {
        return rep;
    }
}
