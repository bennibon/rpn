package com.bennibon.rpn.input;

import java.util.Scanner;

public class CommandLineStrategy extends InputStrategy {
	
	final private static String BANNER = "Reverse Polish Notation Calculator\n2017 Ben Bonavia\n";
	
	final private static String PROMPT = "> ";
	
	
	@Override
	public void process() {
		displayBanner();
		Scanner inputScanner = new Scanner(System.in);
		awaitInput(inputScanner);
		
		// close scanner when finished
		inputScanner.close(); 
	}
	
	private void displayBanner() {
		System.out.println(BANNER);
	}

	/**
	 * Await input from a scanner.
	 * @param inputScanner The input scanner
	 */
	final private void awaitInput(Scanner inputScanner) {
		
		boolean stopWaiting = false;
		while (!stopWaiting) {
			System.out.print(PROMPT);
			if (inputScanner.hasNextLine()) {
				stopWaiting = calcFacilitator.facilitate(inputScanner.nextLine());
			}
		}
	}

}
