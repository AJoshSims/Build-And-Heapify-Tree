package buildAndHeapifyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
			if (parent.getRight() != root)
			{
				root.setSibling(parent.getRight());				
			}
			
			else if(parent.getSibling() != null)
			{
				root.setSibling(parent.getSibling().getLeft());
			}
		}
		
		PathNode leftChild = root.getLeft();
		PathNode rightChild = root.getRight();
		if (leftChild != null)
		{
			setSiblingLinks(leftChild);
		}
		if (rightChild != null)
		{
			setSiblingLinks(rightChild);
		}
	}
	
	void printTreeLevels()
	{
		// TODO string justification
		
		String output = "";
		
		String level = "Root";
		int levelNumInt = 0;
		
		PathNode root = tempPath.get(1);
		while (root != null)
		{
			output += "\n";
			output += level + ":";
			output += root;
			
			PathNode sibling = root.getSibling();
			while (sibling != null)
			{
				output += sibling + "-> ";
				sibling = sibling.getSibling();
			}
			output = output.substring(0, output.length() - 3);
			
			root = root.getLeft();
			++levelNumInt;
			level = "Level " + levelNumInt;
		}
		
		System.out.print(output);
	}
	
	private void heapify()
	{
		
	}
}
