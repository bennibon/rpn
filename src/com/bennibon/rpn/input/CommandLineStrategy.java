package com.bennibon.rpn.input;

import java.util.Scanner;

import com.bennibon.rpn.calc.types.Operator;

/**
 * The command line input strategy. 
 * <p>
 * This strategy takes the input from the command line.
 * @author Ben Bonavia 2017
 */
public class CommandLineStrategy extends InputStrategy {
	
	/** string to quit. */
	private static final String QUIT = "quit";
	
	/** The opening banner with quit instruction. */
	private static final String CLI_BANNER = BANNER + "\nType \"quit\" to quit\n";
	
	@Override
	public void execute() {
		System.out.println(CLI_BANNER);
		Scanner inputScanner = new Scanner(System.in);
		awaitInput(inputScanner);
		
		// close scanner when finished
		inputScanner.close(); 
	}
	
	/**
	 * Await input from a scanner.
	 * @param inputScanner The input scanner
	 */
	final private void awaitInput(Scanner inputScanner) {
		
		while (true) {
			System.out.print(PROMPT);
			if (inputScanner.hasNextLine()) {
				String inputLine = inputScanner.nextLine();
				if (inputLine.toLowerCase().contains(QUIT)) {
					break;
				}
				calc.process(inputLine);
			}
		}
	}

}
