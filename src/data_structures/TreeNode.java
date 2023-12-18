package data_structures;

/**
 * This class represents a node in the decision tree, where the decision tree is the larger tree 
 * that manages what questions are asked and other important tasks. As a node, this class should 
 * keep track of the question, the children (either yes or no), and information for making a 
 * guess.
 * 
 * @author Steven Bui
 */
public class TreeNode {
	
	/** The question that this specific node asks in the decision tree */
	private String question;
	
	/** The next TreeNode to access after this TreeNode if the user's response is 'yes' */
	TreeNode yes;
	
	/** The next TreeNode to access after this TreeNode if the user's response is 'no' */
	TreeNode no;
	
	/** 
	 * This is only used if this node is a LEAF node, which means that this node is at the end 
	 * of the decision tree. This stores the decision tree's best guess.
	 */
	String guess;
	
	
	/**
	 * Constructor for the TreeNode class.
	 * 
	 * @param question the question asked by the node
	 */
	public TreeNode(String question) {
		this.question = question;
	}
	
	/**
	 * Checks if this TreeNode is a leaf node, meaning it is at the end of the decision tree.
	 * 
	 * @return true if this node is a leaf node
	 */
	public boolean isLeaf() {
		return yes == null && no == null;
	}
	
	/**
	 * If this TreeNode is a leaf node, we set the private field guess to the best guess.
	 * 
	 * @param guess the best guess for the decision tree
	 */
	public void setGuess(String guess) {
		this.guess = guess;
	}
	
	/**
	 * If this TreeNode is a leaf node, we would return the best guess.
	 * 
	 * @return the best guess for the decision tree
	 */
	public String getGuess() {
		return guess;
	}
	
}
