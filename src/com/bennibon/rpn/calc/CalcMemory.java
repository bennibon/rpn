package com.bennibon.rpn.calc;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 *  * The memory of the calculator.
 * <p>
 * The memory consists of:
 * <li> The current stack which is to operated on
 * <li> The history of previous stack states
 * @author Ben Bonavia 2017
 */
public final class CalcMemory {

    /** The current stack to be operated upon. */
    private Deque<Double> stack;

    /** The history of previous stacks. */
    private final Deque<Deque<Double>> history;

    /**
     * Constructor.
     */
    CalcMemory() {
        stack = new ArrayDeque<>();
        history = new ArrayDeque<>();
    }

    /**
     * @return The history of previous stack states.
     */
    public final Deque<Deque<Double>> history() {
        return history;
    }

    /**
     * @return The stack printed as a {@link String}.
     */
    public final String printStack() {
        final DecimalFormat formatter = new DecimalFormat("#.##########");
        final StringBuilder stackSb = new StringBuilder("stack: ");
        stackSb.append(stack.stream().map(d -> formatter.format(d.doubleValue())).collect(Collectors.joining(" ")));
        return stackSb.toString();
    }

    public void restorePrevious() {
        if (!history.isEmpty()) {
            stack = new ArrayDeque<>(history.removeLast());
        }
    }

    /**
     * Save the current stack state to history.
     */
    public void save() {
        history.addLast(new ArrayDeque<>(stack));
    }

    /**
     * @return The current stack.
     */
    public final Deque<Double> stack() {
        return stack;
    }

}
