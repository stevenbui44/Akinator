package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class to test the DataSet class's methods.
 * 
 * @author Steven Bui
 */
class DataSetTest {
	
	/** The default empty data set used for testing */
	private DataSet dataSet;
	
	/**
	 * Sets up the DataSet before running each test.
	 */
	@BeforeEach
	public void setUp() {
		List<Character> characters = new ArrayList<>();
		dataSet = new DataSet(characters);
		assertTrue(dataSet.isEmpty());
		assertEquals(0, dataSet.numCharacters());
		List<Character> list = dataSet.getCharacters();
		assertEquals(0, list.size());
	}

	/**
	 * Tests the addCharacter() method.
	 */
	@Test
	public void testAddCharacter() {
		// Add 1 character
		Character c1 = new Character("c1");
		dataSet.addCharacter(c1);
		assertFalse(dataSet.isEmpty());
		assertEquals(1, dataSet.numCharacters());
		
		List<Character> list = dataSet.getCharacters();
		assertEquals(1, list.size());
		String string = "";
		for (int i = 0; i < list.size(); i++) {
			string += list.get(i).getName() + ", ";
		}
		assertEquals("c1, ", string);
		
		
		
		// Add 2 characters
		Character c2 = new Character("c2");
		dataSet.addCharacter(c2);
		assertFalse(dataSet.isEmpty());
		assertEquals(2, dataSet.numCharacters());
		
		string = "";
		for (int i = 0; i < list.size(); i++) {
			string += list.get(i).getName() + ", ";
		}
		assertEquals("c1, c2, ", string);
		
		
		
		// Add a character with the same name (ex. Steve Rogers, Steve Harrington)
		Character c3 = new Character("c1");
		dataSet.addCharacter(c3);
		assertFalse(dataSet.isEmpty());
		assertEquals(3, dataSet.numCharacters());
		
		string = "";
		for (int i = 0; i < list.size(); i++) {
			string += list.get(i).getName() + ", ";
		}
		assertEquals("c1, c2, c1, ", string);
		
		
		
		// Add a character with multiple tokens in the name
		Character c4 = new Character("Steven Bui");
		dataSet.addCharacter(c4);
		assertFalse(dataSet.isEmpty());
		assertEquals(4, dataSet.numCharacters());
		
		string = "";
		for (int i = 0; i < list.size(); i++) {
			string += list.get(i).getName() + ", ";
		}
		assertEquals("c1, c2, c1, Steven Bui, ", string);
	}
	
	@Test
	public void testFilterByAttribute() {
		// Add 1 character with 3 attributes
		Character c1 = new Character("c1");
		dataSet.addCharacter(c1);
		c1.addAttribute("a1");
		c1.addAttribute("a2");
//		c1.addAttribute("a3", false);
		
		// Make sure we registered the attributes right
		assertEquals(2, dataSet.numAttributes());
		List<String> attributes = dataSet.getAvailableAttributes();
		assertEquals(2, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			assertEquals("a" + (i + 1), attributes.get(i));
		}
		
		// Tests filtering by attribute
		DataSet d1 = dataSet.filterByAttribute("a1");
		assertEquals(1, d1.numCharacters());
		assertEquals("c1", d1.getCharacters().get(0).getName());
		
		
		
		// Adds 2 more characters, 1 with the attribute and 1 without
		Character c2 = new Character("c2");
		Character c3 = new Character("c3");
		dataSet.addCharacter(c2);
		dataSet.addCharacter(c3);
//		c2.addAttribute("a1", false);
		c3.addAttribute("a1");
//		
		// Tests filtering by attribute
		DataSet d2 = dataSet.filterByAttribute("a1");
		assertEquals(2, d2.numCharacters());
		assertEquals("c1", d2.getCharacters().get(0).getName());
		assertEquals("c3", d2.getCharacters().get(1).getName());
		
		
		
		// Tests what happens for attributes that were not explicitly stated
		DataSet d3 = dataSet.filterByAttribute("a2");
		assertEquals(1, d3.numCharacters());
		assertEquals("c1", d3.getCharacters().get(0).getName());
		
		
		
		// Tests what happens for an attribute with 0 characters
		DataSet d4 = dataSet.filterByAttribute("a3");
		assertEquals(0, d4.numCharacters());
		assertTrue(d4.isEmpty());
		
		
		
		// Tests what happens for an invalid attribute
		DataSet d5 = dataSet.filterByAttribute("a4");
		assertEquals(0, d5.numCharacters());
		assertTrue(d5.isEmpty());
	}

}
