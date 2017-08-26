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

    /** Formatter to ensure the printed stack number is no larger than 10 decimal places. */
    private static final DecimalFormat TEN_DEC_PLACE_FORMATTER = new DecimalFormat("#.##########");

    /** The current stack to be operated upon. */
    private Deque<Double> stack;

    /** The history of previous stacks. */
    private final Deque<Deque<Double>> history;

    /**
     * Constructor.
     */
    public CalcMemory() {
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
        final StringBuilder stackSb = new StringBuilder("stack: ");
        stackSb.append(stack.stream().map(d -> TEN_DEC_PLACE_FORMATTER.format(d.doubleValue()))
                .collect(Collectors.joining(" ")));
        return stackSb.toString();
    }

    /**
     * Restore the stack to its previous state.
     */
    public void restorePrevious() {
        if (!history.isEmpty()) {
            stack = new ArrayDeque<>(history.removeLast());
        } else {
            stack.clear();
        }
    }

    /**
     * Save the current stack state to history.
     */
    public void save() {
        if (!stack.isEmpty()) {
            history.addLast(new ArrayDeque<>(stack));
        }
    }

    /**
     * @return The current stack.
     */
    public final Deque<Double> stack() {
        return stack;
    }

}
