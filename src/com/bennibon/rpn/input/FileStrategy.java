package com.bennibon.rpn.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileStrategy extends InputStrategy {

	private String fileName;
	
	public FileStrategy(String file) {
		fileName = file;
	}

	@Override
	public void process() {
		displayBanner();
		// try to open file
		File instructionsFile = new File(fileName);
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader(instructionsFile));
			
			fileReader.lines().forEach(l -> {
				System.out.println(PROMPT + l);
				calcFacilitator.facilitate(l);
			});
		} catch (FileNotFoundException e) {
			System.err.println(fileName + " cannot be found.");
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.err.println("Failed to close file " + fileName);
			}
		}
		
	}

}
