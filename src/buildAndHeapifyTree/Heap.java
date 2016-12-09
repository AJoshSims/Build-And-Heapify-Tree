package buildAndHeapifyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import utilities.CommonConstants;

final class Heap
{
	public static final int ROOT_INDEX = 1;
	
	private ArrayList<PathNode> tempPath;
	
	Heap()
	{
		tempPath = new ArrayList<PathNode>();
		tempPath.add(null);
	}
	
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
		throws FileNotFoundException
	{
		try(Scanner fileParser = new Scanner(new File(inputFile)))
		{
			Scanner pathParser = null;
			Integer vertex = null;
			String path = null;
			PathNode pathNode = null;
			while (fileParser.hasNextLine())
			{
				// TODO error handling for lines
				
				pathNode = new PathNode();
				tempPath.add(pathNode);
				
				path = fileParser.nextLine();
				pathParser = new Scanner(path);
				while (pathParser.hasNext())
				{
					// TODO error handling for content of lines
					
					vertex = (pathParser.nextInt());
					pathNode.addToPath(vertex);
				}
			}
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
		
		PathNode root = tempPath.get(ROOT_INDEX);
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
	
	void heapify()
	{
//		PathNode start = tempPath.get(tempPath.size() - 1);
//		
//		PathNode parent01 = start.getParent();
//		while (parent01 != null)
//		{
//			PathNode parent02 = parent01;
//			while (parent02 != null)
//			{
//				PathNode leftChildOfParent = parent02.getLeft();
//				PathNode rightChildOfParent = parent02.getRight();
//				
//				PathNode target = null;
//				if (
//					(leftChildOfParent != null)
//					&& (leftChildOfParent.getPathLength() 
//					< parent02.getPathLength())
//					&& ((rightChildOfParent == null)
//					|| (leftChildOfParent.getPathLength() 
//					< rightChildOfParent.getPathLength())))
//				{	
//					target = leftChildOfParent;
//				}
//				else if (
//					(rightChildOfParent != null)
//					&& (rightChildOfParent.getPathLength() 
//					< parent02.getPathLength()))
//				{
//					target = rightChildOfParent;
//				}
//				
//				if (target != null)
//				{
//					target.setParent(parent02.getParent());
//					parent02.setParent(leftChildOfParent);
//					
//					PathNode leftChildTemp = target.getLeft();
//					target.setLeft(parent02.getLeft());
//					parent02.setLeft(leftChildTemp);
//					
//					PathNode rightChildTemp = target.getRight();
//					target.setRight(parent02.getRight());
//					parent02.setRight(rightChildTemp);
//					
//					PathNode siblingTemp = target.getSibling();
//					target.setSibling(parent02.getSibling());
//					parent02.setSibling(siblingTemp);
//				}
//				
//				parent02 = parent02.getSibling();
//			}
//			
//			parent01 = parent01.getParent();
//		}
	}
}
