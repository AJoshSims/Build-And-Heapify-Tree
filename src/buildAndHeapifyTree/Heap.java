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
		
		// TODO remove
		for (PathNode thing01 : tempPath)
		{
			if (thing01 != null)
			{
				for (Integer thing02 : thing01.getPath())
				{
					System.out.print(thing02 + " ");
				}
				System.out.println("");
			}
		}

	}
//	
//	private PathNode buildCompleteTree(
//		ArrayList<PathNode> tempPaths,
//		int index,
//		PathNode newParent)
//	{
//		
//	}
//	
//	private void setLevelEnd(PathNode root)
//	{
//		
//	}
//	
//	private void setSiblingLinks(PathNode root)
//	{
//		
//	}
//	
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
