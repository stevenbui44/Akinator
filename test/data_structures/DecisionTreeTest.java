package data_structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DataSet;
import data.Character;

/**
 * JUnit test class to test the DecisionTree class's methods.
 * 
 * @author Steven Bui
 */
class DecisionTreeTest {

	/** The default empty decision tree used for testing */
	private DecisionTree tree;
	
	/** The default empty data set used for testing */
	private DataSet dataSet;
	
	private String initialQuestion = "a1";
	
	/**
	 * Sets up the DecisionTree and DataSet before running each test. Also fills the data set with 
	 * 5 characters, each with 3 attributes.
	 */
	@BeforeEach
	public void setUp() {
		tree = new DecisionTree(initialQuestion);
		assertNotNull(tree);
		dataSet = new DataSet();
		assertTrue(dataSet.isEmpty());
		
		// Let's create 5 characters, each with 3 attributes
		/*
		 * a1 = example (3/8)
		 * a2 = example (5/8)
		 * a3 = all false
		 * a4 = 1/2 true, 1/2 false
		 * a5 = all true
		 */
		Character c1 = new Character("c1");
		c1.addAttribute("a1");
		c1.addAttribute("a2");
//		c1.addAttribute("a3", false);
//		c1.addAttribute("a4", false);
//		c1.addAttribute("a5", true);
		Character c2 = new Character("c2");
		c2.addAttribute("a1");
		c2.addAttribute("a2");
//		c2.addAttribute("a3", false);
//		c2.addAttribute("a4", false);
		c2.addAttribute("a5");
		Character c3 = new Character("c3");
		c3.addAttribute("a1");
//		c3.addAttribute("a2", false);
//		c3.addAttribute("a3", false);
//		c3.addAttribute("a4", false);
		c3.addAttribute("a5");
		Character c4 = new Character("c4");
//		c4.addAttribute("a1", false);
//		c4.addAttribute("a2", false);
//		c4.addAttribute("a3", false);
//		c4.addAttribute("a4", false);
		c4.addAttribute("a5");
		Character c5 = new Character("c5");
//		c5.addAttribute("a1", false);
		c5.addAttribute("a2");
//		c5.addAttribute("a3", false);
		c5.addAttribute("a4");
		c5.addAttribute("a5");
		Character c6 = new Character("c6");
//		c6.addAttribute("a1", false);
		c6.addAttribute("a2");
//		c6.addAttribute("a3", false);
		c6.addAttribute("a4");
		c6.addAttribute("a5");
		Character c7 = new Character("c7");
//		c7.addAttribute("a1", false);
		c7.addAttribute("a2");
//		c7.addAttribute("a3", false);
		c7.addAttribute("a4");
		c7.addAttribute("a5");
		Character c8 = new Character("c8");
//		c8.addAttribute("a1", false);
//		c8.addAttribute("a2", false);
//		c8.addAttribute("a3", false);
		c8.addAttribute("a4");
		c8.addAttribute("a5");
		
		
		c1.addAttribute("a6");
		c2.addAttribute("a6");
//		c3.addAttribute("a6", false);
		c4.addAttribute("a6");
//		c5.addAttribute("a6", false);
//		c6.addAttribute("a6", false);
		c7.addAttribute("a6");
//		c8.addAttribute("a6", false);
		
		c1.addAttribute("a7");
//		c2.addAttribute("a7", false);
		c3.addAttribute("a7");
//		c4.addAttribute("a7", false);
//		c5.addAttribute("a7", false);
//		c6.addAttribute("a7", false);
		c7.addAttribute("a7");
		c8.addAttribute("a7");
		
//		c1.addAttribute("a8", false);
		c2.addAttribute("a8");
		c3.addAttribute("a8");
//		c4.addAttribute("a8", false);
//		c5.addAttribute("a8", false);
		c6.addAttribute("a8");
//		c7.addAttribute("a8", false);
//		c8.addAttribute("a8", false);
		
//		c1.addAttribute("a9", false);
//		c2.addAttribute("a9", false);
//		c3.addAttribute("a9", false);
//		c4.addAttribute("a9", false);
//		c5.addAttribute("a9", false);
//		c6.addAttribute("a9", false);
//		c7.addAttribute("a9", false);
//		c8.addAttribute("a9", true);
		
		
		dataSet.addCharacter(c1);
		dataSet.addCharacter(c2);
		dataSet.addCharacter(c3);
		dataSet.addCharacter(c4);
		dataSet.addCharacter(c5);
		dataSet.addCharacter(c6);
		dataSet.addCharacter(c7);
		dataSet.addCharacter(c8);
		assertEquals(8, dataSet.numCharacters());
//		assertEquals(5, dataSet.numAttributes());
	}
	
	
	/**
	 * Tests the calculateInitialEntropy method. The method should be private, but it's set to 
	 * protected to test its functionality.
	 */
	@Test
	public void testCalculateInitialEntropy() {

		// Calculate entropy for just a1 (c1, c2, c3), so prob = 3.0/8.0
		double expected1 = (-3.0/8.0 * log2(3.0/8.0)) + (-5.0/8.0 * log2(5.0/8.0));
		double actual1 = tree.calculateInitialEntropy(dataSet);
		assertEquals(expected1, actual1, 0.001);		// 0.9544340029249649
//		System.out.println(actual1);
		
		
		// Calculate entropy for just a3 (none), so prob = 0.0
		initialQuestion = "a3";
		setUp();
		double expected2 = 0.0;
		double actual2 = tree.calculateInitialEntropy(dataSet);
		assertEquals(expected2, actual2, 0.001);
		
		
		// Calculate entropy for an invalid attribute, so prob = 0.0
		initialQuestion = "a5";
		setUp();
		double expected3 = 0.0;
		double actual3 = tree.calculateInitialEntropy(dataSet);
		assertEquals(expected3, actual3, 0.001);
//		initialQuestion = "a1";
	}
	
	private double log2(double x) {
		if (x == 0.0) {
			return 0.0;
		}
		return Math.log(x) / Math.log(2);
	}
	
	
	/**
	 * Tests the calculateNewEntropy method as we get deeper into the tree, not just the start. The 
	 * method should be private, but it's set to protected to test its functionality.
	 */
	@Test
	public void testCalculateNewEntropySteps() {
		// TODO: test info gain when you get deeper into the tree
	}
	
	
	@Test
	/**
	 * Tests the calculateNewEntropy method at just the start step. The method should be private, 
	 * but it's set to protected to test its functionality.
	 */
	public void testCalculateNewEntropy() {
	
		// Calculate info gain for a2, where we started with a1
		// 5 characters total   = 8 total
		// attribute = a2 		= 5 with attribute 
		// lastAsked = a1		= 2 with attribute and lastAsked
		// lastAsked = a1		= 1 with lastAsked only
		double expected1 = ((5.0/8.0) * ((-2.0/5.0 * log2(2.0/5.0)) + (-3.0/5.0 * log2(3.0/5.0)))) + 
				((3.0/8.0) * ((-1.0/3.0 * log2(1.0/3.0)) + (-2.0/3.0 * log2(2.0/3.0))));
		double actual1 = tree.calculateNewEntropy(dataSet, "a2", 0.0);
		assertEquals(expected1, actual1, 0.001);
		
		
		// Calculate info gain for a4, where we started with a2
		initialQuestion = "a2";
		setUp();
		
		// 8 characters total	= 8 total
		// attribute = a4		= 4 with attribute
		// lastAsked = a2		= 3 with attribute and lastAsked
		// lastAsked = a2		= 2 with lastAsked only
		double expected2 = ((4.0/8.0) * ((-3.0/4.0 * log2(3.0/4.0)) + (-1.0/4.0 * log2(1.0/4.0)))) + 
				((4.0/8.0) * ((-2.0/4.0 * log2(2.0/4.0)) + (-2.0/4.0 * log2(2.0/4.0))));
		double actual2 = tree.calculateNewEntropy(dataSet, "a4", 0.0);
		assertEquals(expected2, actual2, 0.001);
		
		
		// Calculate info gain when numBoth = 0
		initialQuestion = "a1";
		setUp();
		
		// numCharacters 		= 8
		// numAttributes (a4) 	= 4
		// numBoth (a4 + a1)	= 0
		// numOne (just a1)		= 3
		double expected3 = ((4.0/8.0) * ((-0.0/4.0 * log2(0.0/4.0)) + (-4.0/4.0 * log2(4.0/4.0)))) + 
				((4.0/8.0) * ((-3.0/4.0 * log2(3.0/4.0)) + (-1.0/4.0 * log2(1.0/4.0))));
		double actual3 = tree.calculateNewEntropy(dataSet, "a4", 0.0);
		assertEquals(expected3, actual3, 0.001);
	}
	
	
	@Test
	public void testChooseBestQuestion() {
		
		initialQuestion = "a1";
		setUp();
		
		tree.chooseBestQuestion(dataSet, true);
		System.out.println();
		tree.chooseBestQuestion(dataSet, false);
		
		// numCharacters 		= 8
		// numAttributes (a4) 	= 5
		// numBoth (a4 + a1)	= 2
		// numOne (just a1)		= 1
//		System.out.println(tree.calculateNewEntropy(dataSet, "a2", 0.0));
		
		// 0.0 (numAttributes == 0)
//		System.out.println(tree.calculateNewEntropy(dataSet, "a3", 0.0));
		
		// numCharacters 		= 8
		// numAttributes (a4) 	= 4
		// numBoth (a4 + a1)	= 0
		// numOne (just a1)		= 3
//		System.out.println(tree.calculateNewEntropy(dataSet, "a4", 0.0));
		
		// 0.0 (numCharacters == numAttributes
//		System.out.println(tree.calculateNewEntropy(dataSet, "a5", 0.0));
	}
	
	
	
	
	
	
	

}
