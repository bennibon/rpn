package com.bennibon.rpn;

import com.bennibon.rpn.input.CommandLineStrategy;
import com.bennibon.rpn.input.FileStrategy;
import com.bennibon.rpn.input.InputStrategy;

/**
 * Reverse Polish Notation Calculator.
 * @author Ben Bonavia 2017
 */
final public class RpnCalculator {

    /**
     * Program entry.
     * @param args The program arguments
     */
    public static void main(final String[] args) {
        final InputStrategy inputStrategy = determineInputStrategy(args);
        inputStrategy.execute();
    }

    /**
     * Determine where the input will come from to use the correct strategy to read it.
     * <p>
     * The input can come from the command line or from a file.
     * <li> Command Line: {@link args} is empty, the default strategy
     * <li> File: {@link args} shall contain -f and a filename.
     * 
     * @param args The application arguments
     * @return The correct input strategy
     */
    private static InputStrategy determineInputStrategy(final String[] args) {
        InputStrategy strategy = null;
        if (args.length == 0) {
            strategy = new CommandLineStrategy();
        } else if (args.length == 2 && args[0].equals("-f")) {
            strategy = new FileStrategy(args[1]);
        } else {
            printUsage();
            System.exit(-1);
        }
        return strategy;
    }

    /**
     * Print the usage if so there is a somewhat graceful exit.
     */
    private static void printUsage() {
        final String usageString = "Reverse Polish Notation Calculator\nUsage\n"
                + "\t-f <filename>\tLoad instructions from file";
        System.out.println(usageString);
    }

}
