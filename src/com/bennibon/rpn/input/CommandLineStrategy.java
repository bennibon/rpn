package com.bennibon.rpn.input;

import java.util.Scanner;

public class CommandLineStrategy extends InputStrategy {
	
	@Override
	public void process() {
		displayBanner();
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
		
		boolean stopWaiting = false;
		while (!stopWaiting) {
			System.out.print(PROMPT);
			if (inputScanner.hasNextLine()) {
				stopWaiting = calcFacilitator.facilitate(inputScanner.nextLine());
			}
		}
	}

}
