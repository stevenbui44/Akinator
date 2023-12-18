package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * NOTE: I'm thinking about having a giant CSV file containing all of the possible characters that 
 * can be checked in the decision tree, but idk how practical this is when we need to add more 
 * character traits and would need to update each value in the CSV file 
 * 
 * NOTE: To fix this, we could probably just have multiple CSV files adding updates to the 
 * characters
 */

/**
 * This class represents a character that can be guessed by the decision tree. A character should 
 * keep track of its name, as well as a map that keeps track of an attribute type from the DataSet 
 * and the specific character's attribute. 
 * 
 * @author Steven Bui
 */
public class Character {
	
	/** The name of the character, used for guessing */
	private String name;
	
	/** A map to keep track of a DataSet attribute and this character's specific attribute */
//	Map<String, Boolean> attributes;
	
	/** A list to keep track of the DataSet attributes that this character has */
	List<String> attributes;
	
	
	/**
	 * Constructor for a character, initializing the name and map.
	 * 
	 * @param name the name of the character
	 */
	public Character(String name) {
		this.name = name;
		this.attributes = new ArrayList<>();
	}
	
	/**
	 * Adds an attribute for the character to the character's map of attributes.
	 * 
	 * @param type		the attribute type as identified in the DataSet
	 * @param attribute whether the character has the specific attribute or not
	 */
	public void addAttribute(String attribute) {
//		attributes.put(type, attribute);
		
		if (!attributes.contains(attribute)) {
			attributes.add(attribute);
			DataSet.notifyAttributeChange(this);
		}
		
		// TODO: find a way to increase the number of attributes in DataSet
	}
	
	/**
	 * Gets the specific attribute of the character for a given attribute type
	 * 
	 * @param  type the attribute type as identified in the DataSet
	 * @return the specific attribute of this character
	 */
	public boolean getAttribute(String attribute) {
//		if (attributes.get(type) == null) {
//			return false;
//		}
//		return attributes.get(type);
		
		return attributes.contains(attribute);
	}
	
	/**
	 * Returns the name of the character
	 * 
	 * @return the name of the character
	 */
	public String getName() {
		return this.name;
	}
	
//	/**
//	 * This returns a map of all of the attributes for the character, where the key is a general 
//	 * DataSet attribute and the value is the character's specific attribute.
//	 * 
//	 * @return a map to keep track of a DataSet attribute and this character's specific attribute
//	 */
//	public Map<String, Boolean> getAllAttributes() {
//		return attributes;
//	}
	
	/**
	 * This returns a list of all of the true DataSet attributes for the character.
	 * 
	 * @return a map to keep track of a DataSet attribute and this character's specific attribute
	 */
	public List<String> getAllAttributes() {
		return attributes;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Character character = (Character) o;
		return Objects.equals(name, character.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
