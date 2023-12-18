package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import data.DataSet;

/**
 * This class is used to read in the characters and their attributes from a file called data.txt, 
 * which is basically a CSV file containing the attribute headers at the top, the character names 
 * on each line, and true or false as a matrix for each character, indicating whether a character 
 * has an attribute.
 * 
 * @author Steven Bui
 *
 */
public class DataReader {
	
	/** The data set that should be filled with an input file's characters and attributes */
	DataSet dataSet;
	
	/**
	 * Constructor, needed for other classes to make a DataReader object
	 */
	public DataReader() {
		//
	}
	
	
	/**
	 * Given the name of a .txt input file in input_files/*, this method returns a DataSet 
	 * containing all of the characters in the game and their corresponding attributes.
	 * 
	 * @param file the .txt input file containing all of the characters and attributes
	 * @return	   a data set containing all of the characters and attributes
	 */
	public DataSet read(String file) {
		String path = "input_files/" + file;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			// Reads the first line of just Characters (c1, c2, ...)
			String[] allCharacters = br.readLine().split(",");
			
			
			// Initializes a list of all characters to include in DataSet (all attributes false)
			List<data.Character> list = new ArrayList<>();
			for (int i = 0; i < allCharacters.length; i++) {
				list.add(new data.Character(allCharacters[i]));
			}
			dataSet = new DataSet(list);
			
			
			// Read the remaining lines
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				
				// The attribute (a1) to mark true for a character
				String attribute = tokens[0];
				
				// Stuff like "c1 c2 c3"
				for (int i = 1; i < tokens.length; i++) {					
					data.Character c = dataSet.getCharacter(tokens[i]);
					c.addAttribute(attribute);
				}
				
			}
			
//			System.out.println("numAttributes=" + dataSet.numAttributes());
//			System.out.println("numCharacters=" + dataSet.numCharacters());
//			for (int i = 0; i < dataSet.numAttributes(); i++) {
//				System.out.println(dataSet.getAvailableAttributes().get(i));
//			}
			
			
			
//			// test out the contents of the matrix
//			System.out.print("\t");
//			for (int i = 0; i < dataSet.numCharacters(); i++) {
//				System.out.print(dataSet.getCharacters().get(i).getName() + "\t");
//			}
//			System.out.println();
//			for (int i = 0; i < dataSet.numAttributes(); i++) {
//				String a = dataSet.getAvailableAttributes().get(i);
//				System.out.print(a + "\t");
//				for (int j = 0; j < dataSet.numCharacters(); j++) {
//					data.Character c = dataSet.getCharacters().get(j);
//					if (c.getAttribute(a)) {
//						System.out.print("o");
//					}
//					System.out.print("\t");
//				}
//				System.out.println();
//			}
			
			return dataSet;
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// We need to return something, but we won't get here
		return null;
	}
	
	
}
