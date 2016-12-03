package buildAndHeapifyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

final class Heap
{
	ArrayList<PathNode> tempPath;
	
	Heap()
	{
		tempPath = new ArrayList<PathNode>();
		tempPath.add(null);
	}
	
	void readPaths(String inputFile)
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
		catch (FileNotFoundException e)
		{
			System.err.println(e.getMessage());
			
			// TODO remove
			e.printStackTrace();
			
			System.exit(1);
		}
	}
	
	PathNode buildCompleteTree(
		ArrayList<PathNode> tempPath,
		int index,
		PathNode newParent)
	{
		PathNode currentPathNode = tempPath.get(index);
		
		currentPathNode.setParent(newParent);
		
		int indexOfLeftChild = (2 * index);
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
	
	void setLevelEnd(PathNode root)
	{
		if (root.getRight() == null)
		{			
			root.setIsLevelEnd(true);
			return;
		}
		
		setLevelEnd(root.getRight());
	}
	
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
	
	void printTreeLevels()
	{
		// TODO string justification
		
		String output = "";
		
		String level = "Root";
		int levelNumInt = 0;
		
		PathNode root = tempPath.get(1);
		
		ArrayList<String> siblingStrings = new ArrayList<String>();
		while (root != null)
		{
			output += "\n";
			output += level + ": ";
			
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
				output += siblingString + "-> ";
			}
			siblingStrings.clear();
			
			output = output.substring(0, output.length() - 3);
			
			root = root.getRight();
			++levelNumInt;
			level = "Level " + levelNumInt;
		}
		
		System.out.print(output);
	}
	
	private void heapify()
	{
		PathNode start = tempPath.get(tempPath.size() - 1);
		
		PathNode parent = start.getParent();
		PathNode leftChildOfParent = parent.getLeft();
		PathNode rightChildOfParent = parent.getRight();
		
		PathNode target = null;
		if ((parent.getLeft().getPathLength() < parent.getPathLength())
			&& (leftChildOfParent.getPathLength() 
			< rightChildOfParent.getPathLength()))
		{	
			target = leftChildOfParent;
		}
		else if (rightChildOfParent.getPathLength() < parent.getPathLength())
		{
			target = rightChildOfParent;
		}
		
		if (target != null)
		{
			target.setParent(parent.getParent());
			parent.setParent(leftChildOfParent);
			
			PathNode leftChildTemp = target.getLeft();
			target.setLeft(parent.getLeft());
			parent.setLeft(leftChildTemp);
			
			PathNode rightChildTemp = target.getRight();
			target.setRight(parent.getRight());
			parent.setRight(rightChildTemp);
			
			PathNode siblingTemp = target.getSibling();
			target.setSibling(parent.getSibling());
			parent.setSibling(siblingTemp);
		}
	}
}
