package buildAndHeapifyTree;

import java.io.FileNotFoundException;

import errors.ExitCodes;
import errors.InvalidInputFileException;
import errors.InvalidNumOfCmdLineArgsException;

/**
 * Builds and prints a tree according to a list of paths passed via the 
 * command line as a text file.
 * 
 * <p> Usage: java buildAndHeapifyTree/HeapDriver <input_file_path>
 * 
 * @author Joshua Sims
 * @version 09 December 2016
 */
public final class HeapDriver
{	
	/**
	 * The index of the command line argument which specifies the name of the
	 * input file.
	 */
	private static final int INPUT_FILE_INDEX = 0;
	
	/**
	 * The usage message for this program.
	 */
	private static final String USAGE_MESSAGE = 
		"Usage: java buildAndHeapifyTree/HeapDriver <input_file_path>";
	
	/**
	 * An example of correct input file content.
	 */
	static final String CORRECT_CONTENT =
		"0 4 3" +
        "\n0 1" +
        "\n0 4 1 2 3" +
        "\n0 1 2" +
        "\n0 2" +
        "\n0 4 1 2" +
        "\n0 2 3" +
        "\n0 4" +
        "\n0 1 2 3" +
        "\n0 4 1";
	
	/**
	 * Builds and prints a tree according to a list of paths passed via the 
	 * command line as a text file.
	 * 
	 * @param args - the command line arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			String[] examinedArgs = examineArgs(args);
			String inputFile = examinedArgs[INPUT_FILE_INDEX];
			
			Heap heap = new Heap();
			heap.readPaths(inputFile);
			heap.buildCompleteTree(
				heap.getTempPath(), Heap.ROOT_ENTIRE_TREE_INDEX, null);
			heap.setLevelEnd(
				heap.getTempPath().get(Heap.ROOT_ENTIRE_TREE_INDEX));
			heap.setSiblingLinks(
				heap.getTempPath().get(Heap.ROOT_ENTIRE_TREE_INDEX));
			heap.printTreeLevels();
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
		catch (InvalidInputFileException e)
		{
			System.err.println(e.getMessage());
			System.exit(ExitCodes.INVALID_INPUT_FILE);
		}
	}
	
	/**
	 * Examines the command line arguments are triggers the abortion of the
	 * program if the command line arguments are invalid.
	 * 
	 * @param args - the command line arguments
	 * 
	 * @return the command line arguments, adapted (not semantically) if 
	 *     necessary to make this program work
	 * 
	 * @throws InvalidNumOfCmdLineArgsException - if an invalid number of
	 *     command line arguments was specified
	 */
	private static String[] examineArgs(String[] args) 
		throws InvalidNumOfCmdLineArgsException
	{
		if (args.length != ExitCodes.INVALID_NUM_OF_CMD_LINE_ARGS)
		{
			throw new InvalidNumOfCmdLineArgsException(USAGE_MESSAGE);
		}
		
		return args;
	}
}
