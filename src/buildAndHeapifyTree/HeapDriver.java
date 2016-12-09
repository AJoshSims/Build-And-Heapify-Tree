package buildAndHeapifyTree;

import java.io.FileNotFoundException;

import errors.ExitCodes;
import errors.InvalidNumOfCmdLineArgsException;

/**
 * Builds and prints a tree according to a list of paths passed via the command
 * line as a text file.
 * 
 * @author Joshua Sims
 * @version 09 October, 2016
 */
public final class HeapDriver
{
	private static final int ACCEPTABLE_NUM_OF_ARGS = 1;
	
	private static final int INPUT_FILE_INDEX = 0;
	
	public static void main(String[] args)
	{
		try
		{
			String[] examinedArgs = examineArgs(args);
			String inputFile = examinedArgs[INPUT_FILE_INDEX];
			
			Heap heap = new Heap();
			heap.readPaths(inputFile);
			heap.buildCompleteTree(heap.getTempPath(), Heap.ROOT_INDEX, null);
			heap.setLevelEnd(heap.getTempPath().get(Heap.ROOT_INDEX));
			heap.setSiblingLinks(heap.getTempPath().get(Heap.ROOT_INDEX));
			heap.printTreeLevels();
			
//			System.out.println("");
			
//			heap.heapify();
//			heap.printTreeLevels();
		}
		
		catch (InvalidNumOfCmdLineArgsException e)
		{
			System.err.println(e.getMessage());
			System.exit(ExitCodes.INVALID_NUM_OF_CMD_LINE_ARGS);
		}
		catch (FileNotFoundException e)
		{
			System.err.println(e.getMessage());
			System.exit(ExitCodes.FILE_NOT_FOUND);
		}
	}
	
	private static String[] examineArgs(String[] args) 
		throws InvalidNumOfCmdLineArgsException
	{
		if (args.length != ACCEPTABLE_NUM_OF_ARGS)
		{
			throw new InvalidNumOfCmdLineArgsException();
		}
		
		return args;
	}
}
