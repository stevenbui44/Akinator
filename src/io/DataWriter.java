package io;

import data.DataSet;

/**
 * This class is used to make large updates to the data.txt file, which contains all of the 
 * characters and attributes used in the game. This class should enable adding a new character, 
 * adding a new attribute, and doing both simultaneously.
 * 
 * Perhaps consider being able to combine another .txt file with the data.txt file to create an 
 * updated data.txt file too.
 * 
 * @author Steven Bui
 */
public class DataWriter {
	
	// Consructor, needed for other classes to make a DataWriter object
	public DataWriter() {
		// ...
	}
	
	
	/**
	 * Given the name of a .txt file to write to, this method creates a new .txt file with
	 * characters and attributes that can be read by a DataReader class.
	 * 
	 * @param file 	  the .txt output file to write the characters and attributes to
	 * @param dataSet the data set with characters and attributes to write to a file
	 */
	public void write(String file, DataSet dataSet) {
		
	}
	
	
//	public void update(String original, String update) {
//		
//	}
}
