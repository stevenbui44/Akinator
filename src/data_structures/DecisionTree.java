package data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DataSet;

/**
 * This class represents the decision tree itself, which asks the user questions and updates the 
 * tree according to the user's responses. This tree is made up of TreeNode objects.
 * 
 * @author Steven Bui
 */
public class DecisionTree {

	/** TreeNode representing the current question in the decision tree */
//	private TreeNode current;
	
	/** Represents the question that was last asked, which the user is currently answering */
	private String lastAsked;
	
	
	/**
	 * Constructor for the decision tree, starting with the initial question
	 * 
	 * @param initialQuestion the first question in the decision tree
	 */
	public DecisionTree(String initialQuestion) {
//		current = new TreeNode(initialQuestion);
		lastAsked = initialQuestion;
	}
	
	
	/**
	 * This is a method that chooses the next question to ask in the decision tree given a data 
	 * set of all the possible attributes.  
	 * 
	 * @param dataSet a data set containing all of the possible attributes
	 * @param choice  whether the user's answer to the attribute is "yes" or "no"
	 * @return 		  an updated data set with only characters with the user's choice
	 */
	public DataSet chooseBestQuestion(DataSet dataSet, boolean choice) {
		
		// This will be the attribute of the dataSet, returned by this method
		String bestQuestion = null;
		
		// Information gain associated with the best question to ask so far
		double bestInfoGain = 0;
		
		// Calculate the initial entropy once (using lastAsked)
		double entropyBefore = calculateInitialEntropy(dataSet);
		
		// Creates a DataSet containing just the characters for if lastAsked is true or false (choice)
//		DataSet smallerSet = splitDataSet(dataSet, choice);
		
		
		// Checks each individual attribute to find the most information gain
		for (String attribute : dataSet.getAvailableAttributes()) {
			if (attribute.equals(lastAsked))
				continue;
			
			// Basically H(lastAsked | attribute)
			double entropyAfter = calculateNewEntropy(dataSet, attribute, entropyBefore);				// calculateNewEntropy (below)
//			double infoGain = calculateNewEntropy(smallerSet, attribute, entropyBefore, choice);		// calculateNewEntropy (below)
			
			double infoGain = entropyBefore - entropyAfter;
//			System.out.println(attribute + "=" + entropyAfter);
			System.out.println(attribute + "=" + infoGain);
			
			if (infoGain > bestInfoGain) {
				bestInfoGain = infoGain;
				bestQuestion = attribute;
			}
		}
				
//		dataSet = splitDataSet(dataSet, choice);
		
		// So what i'm thinking is maybe we can store a copy of the decision tree in a separate
		// file, so when we need to access that character again or at least go down that same path,
		// the path will already have some of it made already. 
		
		lastAsked = bestQuestion;
		
		return splitDataSet(dataSet, choice);
		
//		return bestQuestion;
	}
	
	
	/**
	 * Helper method that first calculates the initial entropy for an attribute. This method THEN 
	 * calculates the entropy for that attribute given the other attributes and finds the most
	 * information gain. This method then returns the value of the most information gain.
	 * 
	 * Think of entropyBefore as H(Edible), where the attribute is Edible. This is achieved via
	 * calculateInitialEntropy().
	 * 
	 * Think of newEntropy as H(Edible|Weight). This is achieved in this method, or maybe a 
	 * private method.
	 * 
	 * Information gain is entropyBefore - otherEntropy.
	 * 
	 * @param dataSet		the data set containing all characters
	 * @param attribute		the attribute to find the information gain for
	 * @param entropyBefore the initial entropy from lastAsked
	 * @param choice		whether lastAsked is true or false
	 * @return 				the amount of information gain by asking about this attribute
	 */
	protected double calculateNewEntropy(DataSet dataSet, String attribute, double entropyBefore) {
		
		// TODO: probably do an error check beforehand to not get here
		if (dataSet.numCharacters() == 0) {
			return 0.0;
		}
		
		// 8
		int numCharacters = dataSet.numCharacters();
		
		// 5
		int numAttributes = 0;	// Number of characters with JUST the attribute
		
		// 2
		int numBoth = 0;		// Number of characters with the attribute AND lastAsked
		
		// 1
		int numOne = 0;			// Number of characters with JUST lastAsked
		
		for (data.Character character : dataSet.getCharacters()) {
			if (character.getAttribute(attribute)) {
				numAttributes++;
				
				if (character.getAttribute(lastAsked)) {
					numBoth++;
				}
			}
			else if (character.getAttribute(lastAsked)) {
				numOne++;
			}
		}
		
		// NOTE: set it to entropyBefore so info gain = 0 since this is useless
		if (numAttributes == 0 || numCharacters == numAttributes) {
			return entropyBefore;
		}
		
		// 5/8
		double probA = (double)numAttributes / numCharacters;
		
		// [-2/5 * log2(2/5)] + [-3/5 * log2(3/5)] 
		double entropyA = (-(double)numBoth / numAttributes) * (log2((double)numBoth / numAttributes))
				+ (-(double)(numAttributes - numBoth) / numAttributes) * (log2((double)(numAttributes - numBoth) / numAttributes));
		
		// 3/8
		double probB = ((double)numCharacters - numAttributes) / numCharacters;
		
		// [-1/3 * log2(1/3)] + [-2/3 * log2(2/3)]
		double entropyB = (-(double)numOne / (numCharacters - numAttributes)) * (log2((double)numOne / (numCharacters - numAttributes)))
				+ (-(double)(numCharacters - numAttributes - numOne) / (numCharacters - numAttributes)) * (log2((double)(numCharacters - numAttributes - numOne) / (numCharacters - numAttributes)));
		
		
		
		
//		System.out.println("numCharacters=" + numCharacters);
//		System.out.println("numAttributes=" + numAttributes);
//		System.out.println("numBoth=" + numBoth);
//		System.out.println("numOne=" + numOne);
		
//		System.out.println("probA=" + probA);
//		System.out.println("entropyA=" + entropyA);
		
		
		
		return probA * entropyA + probB * entropyB;
	}


	/**
	 * Helper method to calculate the initial amount of entropy for the LAST ASKED attribute.
	 * 
	 * Think of this as H(Edible). 
	 * 
	 * @param dataSet	the data set used to find entropy
	 * @param attribute the attribute to find the entropy of
	 * @return 			the amount of entropy for a single attribute
	 */
	protected double calculateInitialEntropy(DataSet dataSet) {
		
		// Gets the number of characters in the data set
		int numCharacters = dataSet.numCharacters();
		if (numCharacters == 0) {
			return 0.0;
		}
		
		// Check how many characters DO have the last asked attribute
		int numYes = 0;
		for (data.Character character : dataSet.getCharacters()) {
			if (character.getAttribute(lastAsked) == true) {
				numYes++;
			}
		}
		
		// Find the entropy using this equation
		double prob = (double)numYes / numCharacters;
		if (prob == 0.0 || prob == 1.0) {
			return 0.0;
		}
		return (-prob * log2(prob)) + ((-1.0 + prob) * (log2(1.0 - prob)));
	}
	
	/**
	 * Helper method for calculateInitialEntropy(), used for calculating the initial entropy.
	 * 
	 * @param x the probability to find the log base 2 of
	 * @return	the log base 2 value of the probability
	 */
	private double log2(double x) {
		if (x == 0.0) {
			return 0.0;
		}
		return Math.log(x) / Math.log(2);
	}
	
	
	/**
	 * Helper method for chooseBestQuestion, creating a data set with only characters where the 
	 * attribute == choice 
	 * 
	 * @param dataSet the most updated data set to "prune"
	 * @param choice  whether the character to keep is true or false
	 * @return
	 */
	private DataSet splitDataSet(DataSet dataSet, boolean choice) {
		DataSet set = new DataSet();
		for (data.Character character : dataSet.getCharacters()) {
			if (character.getAttribute(lastAsked) == choice) {
				set.addCharacter(character);
			}
		}
		return set;
	}
	
	
}
