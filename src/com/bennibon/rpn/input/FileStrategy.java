package com.bennibon.rpn.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The file input strategy.
 * <p>
 * This strategy takes the input from a file.
 * 
 * @author Ben Bonavia 2017
 */
final public class FileStrategy extends InputStrategy {

	/** The file name. */
	private String fileName;

	/**
	 * Constructor.
	 * 
	 * @param file The file to take the input from.
	 */
	public FileStrategy(String file) {
		fileName = file;
	}

	@Override
	final public void execute() {
		System.out.println(BANNER);
		// try to open file
		File instructionsFile = new File(fileName);
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader(instructionsFile));

			// Display all lines to stdout as if they were manually entered.
			fileReader.lines().forEach(l -> {
				System.out.println(PROMPT + l);
				calc.process(l);
			});
		} catch (FileNotFoundException e) {
			System.err.println(fileName + " cannot be found.");
		} finally {
			// clean up
			try {
				fileReader.close();
			} catch (IOException e) {
				System.err.println("Failed to close file " + fileName);
			}
		}
	}
}
