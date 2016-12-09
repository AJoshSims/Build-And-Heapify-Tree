package buildAndHeapifyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import errors.InvalidInputFileException;

import java.util.InputMismatchException;

import utilities.CommonConstants;

/**
 * Models a tree -- a heap, if heapified. Each node in the tree represents
 * the path from some vertex to another. If heapified, the tree will be 
 * structured such that the each parent node represents a path length which
 * is less than the path length of either of its children nodes.
 * 
 * <p>Heapification has not been implemented.
 * 
 * @author Joshua Sims
 * @version 09 December 2016
 *
 */
final class Heap
{
	/**
	 * The index of the root of the entire tree.
	 */
	public static final int ROOT_ENTIRE_TREE_INDEX = 1;
	
	/**
	 * Holds the path information given via the input file. 
	 */
	private ArrayList<PathNode> tempPath;
	
	/**
	 * The size of tempPath with only the null placeholder
	 */
	private static final int TEMP_PATH_SIZE_WITH_PLACEHOLDER = 1;
	
	/**
	 * Initializes the dynamically sized array which will hold the path 
	 * informatino given via the input file.
	 */
	Heap()
	{
		tempPath = new ArrayList<PathNode>();
		tempPath.add(null);
	}
	
	/**
	 * Returns the path information given via the input file.
	 * 
	 * @return the path information given via the input file
	 */
	ArrayList<PathNode> getTempPath()
	{
		return tempPath;
	}
	
	/**
	 * Reads the input file given at the command line and places the contents 
	 * of each line into the path field found in each PathNode object. The 
	 * order of PathNode objects is the same as found in the text file. 
	 * Adds the new PathNode object to tempPath starting at tempPath[1].
	 * 
	 * @param inputFile - name of the input file to be read
	 * @throws FileNotFoundException - if the input file was not found
	 */
	void readPaths(String inputFile) 
		throws FileNotFoundException, InvalidInputFileException
	{
		try(Scanner fileParser = new Scanner(new File(inputFile)))
		{
			Integer vertex = null;
			String path = null;
			PathNode pathNode = null;
			while (fileParser.hasNextLine())
			{
				pathNode = new PathNode();
				tempPath.add(pathNode);
				
				path = fileParser.nextLine();
				try (Scanner pathParser = new Scanner(path))
				{
					while (pathParser.hasNext())
					{
						vertex = (pathParser.nextInt());
						pathNode.addToPath(vertex);
					}
				}
				catch (InputMismatchException e)
				{
					throw new InvalidInputFileException(
						HeapDriver.CORRECT_CONTENT);
				}
			}
		}
		
		if (tempPath.size() == TEMP_PATH_SIZE_WITH_PLACEHOLDER)
		{
			throw new InvalidInputFileException(
				HeapDriver.CORRECT_CONTENT);
		}
	}
	
	/**
	 * Recursively builds a complete binary tree. Places PathNode objects in 
	 * tempPath into a complete binary tree in order of appearance in the text 
	 * file. The left child of a parent located at tempPaths[index] is found 
	 * at tempPath[2*index] and the right child is found at 
	 * tempPath[2*index + 1].
	 * 
	 * @param tempPath - holds the path information given via the input file 
	 * @param index - the index of the current node in tempPath
	 * @param newParent - parent of the current node
	 * 
	 * @return a reference to the node just placed in the tree
	 */
	PathNode buildCompleteTree(
		ArrayList<PathNode> tempPath,
		int index,
		PathNode newParent)
	{
		PathNode currentPathNode = tempPath.get(index);
		
		currentPathNode.setParent(newParent);
		
		// The index of a left child is twice the index of its parent.
		int indexOfLeftChild = (2 * index);
		// The index of a right child is twice this index of its parent plus 
		// one.
		int indexOfRightChild = (2 * index) + 1;
		if (indexOfLeftChild < tempPath.size())
		{
			currentPathNode.setLeft(buildCompleteTree(
				tempPath, indexOfLeftChild, currentPathNode));
			
			if (indexOfRightChild < tempPath.size())
			{
				currentPathNode.setRight(buildCompleteTree(
					tempPath, indexOfRightChild, currentPathNode));
			}
		}
		
		return currentPathNode;
	}
	
	/**
	 * Recursive method which specifies which nodes are at the end of their 
	 * level in the tree.
	 * 
	 * @param root - the root of a subtree (or of the entire tree)
	 */
	void setLevelEnd(PathNode root)
	{
		if (root.getRight() == null)
		{			
			root.setIsLevelEnd(true);
			return;
		}
		
		setLevelEnd(root.getRight());
	}
	
	/**
	 * Recursive method that sets the sibling link of each node such that the
	 * sibling of a node is to the left of it, along the same level of the 
	 * tree.
	 * 
	 * @param root - the root of a subtree (or of the entire tree)
	 */
	void setSiblingLinks(PathNode root)
	{
		PathNode parent = root.getParent();
		if (parent != null)
		{
			if (parent.getLeft() != root)
			{
				root.setSibling(parent.getLeft());				
			}
			
			else if(parent.getSibling() != null)
			{
				root.setSibling(parent.getSibling().getRight());
			}
		}
		
		PathNode rightChild = root.getRight();
		PathNode leftChild = root.getLeft();
		if (rightChild != null)
		{
			setSiblingLinks(rightChild);
		}
		if (leftChild != null)
		{
			setSiblingLinks(leftChild);
		}
	}
	
	/**
	 * Prints each level of the tree from left to right.
	 */
	void printTreeLevels()
	{		
		String output = "";
		String level = "Root: ";
		int levelNum = 0;
		String siblingStringsConcatenated = "";
		// The offset necessary to remove a leading arrow.
		final int offset = 3; 
		
		PathNode root = tempPath.get(ROOT_ENTIRE_TREE_INDEX);
		ArrayList<String> siblingStrings = new ArrayList<String>();
		while (root != null)
		{
			output += level;
			
			siblingStrings.add(root.toString());
			PathNode sibling = root.getSibling();
			if (sibling != null)
			{
				while (sibling != null)
				{
					siblingStrings.add(sibling.toString());
					sibling = sibling.getSibling();
				}
				 
				Collections.reverse(siblingStrings);
			}
			for (String siblingString : siblingStrings)
			{
				siblingStringsConcatenated += siblingString + "-> ";
			}
			siblingStrings.clear();
			
			siblingStringsConcatenated = 
				siblingStringsConcatenated.substring(
				CommonConstants.FIRST_CHAR_INDEX, 
				siblingStringsConcatenated.length() - offset);		

			output += siblingStringsConcatenated;
			siblingStringsConcatenated = "";
			
			++levelNum;
			level = "\nLevel " + levelNum + ": ";
			root = root.getRight();
		}
		
		System.out.println(output);
	}
	
	/**
	 * Creates a min-heap structure based on path length.
	 * 
	 * <p>Heapification and, therefore, this method have not been 
	 * implemented.
	 */
	void heapify()
	{
	}
}
