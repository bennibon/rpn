package com.bennibon.rpn.calc.process;

import java.util.Deque;
import java.util.HashMap;
import java.util.function.Consumer;

import com.bennibon.rpn.calc.CalcMemory;
import com.bennibon.rpn.calc.interfaces.CalcOperation;
import com.bennibon.rpn.calc.types.Operator;

/**
 * Input Handler.
 * <p>
 * Provides methods to handle both kinds of inputs:
 * <li>Numbers
 * <li>Operators
 * @author Ben Bonavia 2017
 */
public class InputHandler {

    /** The mathematical operations. */
    private static final HashMap<Operator, CalcOperation> MATH_OPERATIONS;

    /** The control operations. */
    private static final HashMap<Operator, Consumer<CalcMemory>> CONTROL_OPERATIONS;

    static {
        MATH_OPERATIONS = new HashMap<>();
        MATH_OPERATIONS.put(Operator.ADD, (a, b) -> a + b);
        MATH_OPERATIONS.put(Operator.SUBTRACT, (a, b) -> a - b);
        MATH_OPERATIONS.put(Operator.MULTIPLY, (a, b) -> a * b);
        MATH_OPERATIONS.put(Operator.DIVIDE, (a, b) -> a / b);
        MATH_OPERATIONS.put(Operator.SQRT, (a, b) -> Math.sqrt(a));

        CONTROL_OPERATIONS = new HashMap<>();
        CONTROL_OPERATIONS.put(Operator.UNDO, m -> m.restorePrevious());
        CONTROL_OPERATIONS.put(Operator.CLEAR, m -> {
            m.save();
            m.stack().clear();
        });
    }

    /**
     * Handles a number and its effect on the provided {@link CalcMemory}.
     * @param number The number to handle
     * @param memory The Calculator Memory
     */
    public void handleNumber(final Double number, final CalcMemory memory) {
        memory.save();
        memory.stack().addLast(number);
    }

    /**
     * Handles an {@link Operator} and its effect on the provided {@link CalcMemory}.
     * @param op The operator to handle
     * @param memory The Calculator Memory
     */
    public void handleOperator(final Operator op, final CalcMemory memory) {
        if (op.isControlOperator()) {
            performControlOperation(op, memory);
        } else {
            memory.save();
            performMathOperation(op, memory.stack());
        }
    }

    /**
     * Performs a control operation on the {@link CalcMemory}.
     * @param op The operator
     * @param memory The calculator memory
     */
    private void performControlOperation(final Operator op, final CalcMemory memory) {
        CONTROL_OPERATIONS.get(op).accept(memory);
    }

    /**
     * Performs a mathematical operation on the stack.
     * @param op The operator
     * @param stack The stack
     */
    private void performMathOperation(final Operator op, final Deque<Double> stack) {
        Double slave = null;
        Double master = null;
        if (op.isPrimitiveOperator()) {
            slave = stack.removeLast();
        }
        master = stack.removeLast();
        stack.addLast(MATH_OPERATIONS.get(op).performOperation(master, slave));
    }

}
