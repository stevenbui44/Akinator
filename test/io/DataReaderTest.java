package io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DataSet;

class DataReaderTest {
	
	DataReader dr;
	
	@BeforeEach
	public void setUp() {
		dr = new DataReader();
		assertNotNull(dr);
	}

	@Test
	public void testRead() {

		// We should be able to read from this file
		assertDoesNotThrow(() -> {
			
			// The contents of the data set should be right
			DataSet dataSet = dr.read("test_data.txt");
			assertEquals(8, dataSet.numCharacters());
			assertEquals(8, dataSet.numAttributes());
			
			// Check each individual Character
			assertNull(dataSet.getCharacter("c0"));
			
			data.Character c1 = dataSet.getCharacter("c1");
			assertEquals(5, dataSet.getCharacter("c1").getAllAttributes().size());
			assertTrue(c1.getAttribute("a1"));
			assertTrue(c1.getAttribute("a2"));
			assertTrue(c1.getAttribute("a5"));
			assertTrue(c1.getAttribute("a6"));
			assertTrue(c1.getAttribute("a7"));
			
			data.Character c2 = dataSet.getCharacter("c2");
			assertEquals(5, dataSet.getCharacter("c2").getAllAttributes().size());
			assertTrue(c2.getAttribute("a1"));
			assertTrue(c2.getAttribute("a2"));
			assertTrue(c2.getAttribute("a5"));
			assertTrue(c2.getAttribute("a6"));
			assertTrue(c2.getAttribute("a8"));
			
			data.Character c3 = dataSet.getCharacter("c3");
			assertEquals(4, dataSet.getCharacter("c3").getAllAttributes().size());
			assertTrue(c3.getAttribute("a1"));
			assertTrue(c3.getAttribute("a5"));
			assertTrue(c3.getAttribute("a7"));
			assertTrue(c3.getAttribute("a8"));
			
			data.Character c4 = dataSet.getCharacter("c4");
			assertEquals(3, dataSet.getCharacter("c4").getAllAttributes().size());
			assertTrue(c4.getAttribute("a3"));
			assertTrue(c4.getAttribute("a5"));
			assertTrue(c4.getAttribute("a6"));
			
			data.Character c5 = dataSet.getCharacter("c5");
			assertEquals(3, dataSet.getCharacter("c5").getAllAttributes().size());
			assertTrue(c5.getAttribute("a2"));
			assertTrue(c5.getAttribute("a4"));
			assertTrue(c5.getAttribute("a5"));
			
			data.Character c6 = dataSet.getCharacter("c6");
			assertEquals(4, dataSet.getCharacter("c6").getAllAttributes().size());
			assertTrue(c6.getAttribute("a2"));
			assertTrue(c6.getAttribute("a4"));
			assertTrue(c6.getAttribute("a5"));
			assertTrue(c6.getAttribute("a8"));
			
			data.Character c7 = dataSet.getCharacter("c7");
			assertEquals(5, dataSet.getCharacter("c7").getAllAttributes().size());
			assertTrue(c7.getAttribute("a2"));
			assertTrue(c7.getAttribute("a4"));
			assertTrue(c7.getAttribute("a5"));
			assertTrue(c7.getAttribute("a6"));
			assertTrue(c7.getAttribute("a7"));
			
			data.Character c8 = dataSet.getCharacter("c8");
			assertEquals(3, dataSet.getCharacter("c8").getAllAttributes().size());
			assertTrue(c8.getAttribute("a4"));
			assertTrue(c8.getAttribute("a5"));
			assertTrue(c8.getAttribute("a7"));

		});
		
	}

}
