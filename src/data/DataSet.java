package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class represents an entire data set of all of the characters and can offer functionality 
 * for filtering and organizing the data. 
 *  
 * @author Steven Bui
 */
public class DataSet {
	
	/** List containing all of the game's characters */
	List<Character> characters;
	
	/** Set containing all of the possible attribute s*/
	static Set<String> attributes;
	
	// TODO: Instead of having a list of characters, I want to restructure this as a map with the 
	// key being the NAME and the value being a LIST of attributes
	
	
	/**
	 * Default constructor with no parameters, creating an empty data set
	 */
	public DataSet() {
		this(new ArrayList<>());
	}
	
	/** 
	 * Constructor for creating a data set given a list of characters
	 * 
	 * @param characters the list of all characters
	 */
	public DataSet(List<Character> characters) {
		this.characters = characters;
		this.attributes = new HashSet<>();
		
		for (int i = 0; i < characters.size(); i++) {
			attributes.addAll(characters.get(i).getAllAttributes());
		}
	}
	
	public static void notifyAttributeChange(data.Character character) {
		attributes.addAll(character.getAllAttributes());
	}
	
		
	/**
	 * Returns a list of all of the characters in this data set.
	 * 
	 * @return a list of all of the characters in this data set
	 */
	public List<Character> getCharacters() {
		return characters;
	}
	
	
	/**
	 * Gets a specific character from the list of characters given their name
	 * 
	 * @param name name of the character to return
	 * @return	   the character with the given name to return
	 */
	public Character getCharacter(String name) {
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getName().equals(name)) {
				return characters.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Returns the number of characters in this data set 
	 * 
	 * @return the number of characters in this data set
	 */
	public int numCharacters() {
		return characters.size();
	}
	
	/**
	 * Returns the number of attributes that each character in the data set has
	 * 
	 * @return the number of attributes that each character in the data set has
	 */
	public int numAttributes() {
//		return characters.get(0).getAllAttributes().size();
		return attributes.size();
	}
	
	/**
	 * Adds a character to the list of characters in this data set
	 * 
	 * @param character the character to add to the data set
	 */
	public void addCharacter(Character character) {
		characters.add(character);
		attributes.addAll(character.getAllAttributes());
	}
	
	/**
	 * Filters the data set based on a specific attribute, returning a NEW UPDATED data set that 
	 * removes characters without the attribute
	 * 
	 * @param attribute a generic attribute that a character is checked to have
	 * @param value		whether the character has the attribute (yes) or not (no)
	 * @return
	 */
	public DataSet filterByAttribute(String attribute) {
//		List<Character> list = new ArrayList<>();
		DataSet dataSet = new DataSet();
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getAttribute(attribute)) {
				dataSet.addCharacter(characters.get(i));
			}
		}
		return dataSet;
	}
	
	/**
	 * Returns a list of all of the available attributes that a character can have
	 * 
	 * @return a list of all of the available attributes that a character can have
	 */
	public List<String> getAvailableAttributes() {
//		if (isEmpty())
//			return null;
//		Character c = characters.get(0);
//		List<String> list = new ArrayList<>();
//		Map<String, Boolean> map = c.getAllAttributes();
//		for (Entry<String, Boolean> entry : map.entrySet()) {
//			list.add(entry.getKey());
//		}
//		return list;		
		
		return new ArrayList<>(attributes);
	}
	
	/**
	 * Returns true if the data set is empty 
	 * 
	 * @return true if the data set is empty
	 */
	public boolean isEmpty() {
		return characters.size() == 0;
	}
}
