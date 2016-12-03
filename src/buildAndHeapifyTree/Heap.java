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
	
	private PathNode buildCompleteTree(
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
	
//	private PathNode buildCompleteTree(
//		ArrayList<PathNode> tempPaths,
//		int index,
//		PathNode newParent)
//	{
//		
//		PathNode currentPathNode;
//		for (int i = 1; i < (tempPaths.size() - 1); ++i)
//		{			
//			currentPathNode = tempPaths.get(index);
//			int indexMultBy2 = 2 * index;
//			if ((indexMultBy2 + 1) < (tempPaths.size() - 1))
//			{				
//				currentPathNode.setLeft(tempPath.get(indexMultBy2 + 1));
//				tempPath.get(indexMultBy2 + 1).setParent(currentPathNode);
//			}
//			if ((indexMultBy2 + 2) < (tempPaths.size() - 1))
//			{
//				currentPathNode.setRight(tempPaths.get(indexMultBy2 + 2));	
//				tempPath.get(indexMultBy2 + 2).setParent(currentPathNode);
//				tempPath.get(indexMultBy2 + 1).setSibling(tempPaths.get(indexMultBy2 + 2));
//				tempPath.get(indexMultBy2 + 2).setSibling(tempPaths.get(indexMultBy2 + 1));
//			}
//		}
//		
//		return currentPathNode;
//	}
	
	private void setLevelEnd(PathNode root)
	{
		if (root.getLeft() == null)
		{			
			root.setIsLevelEnd(true);
			return;
		}
		
		setLevelEnd(root.getLeft());
	}
	
	private void setSiblingLinks(PathNode root)
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
	
//	private void printTreeLevels()
//	{
//		
//	}
//	
//	private void heapify()
//	{
//		
//	}
}
