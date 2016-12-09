package errors;

/**
 * Indicates that an invalid number of commands line arguments were 
 * passed.
 * 
 * @author Joshua Sims
 * @version 09 October, 2016
 */
public final class InvalidNumOfCmdLineArgsException extends Exception
{
	/**
	 * Initializes this exception with an appropriate error message.
	 */
	public InvalidNumOfCmdLineArgsException()
	{
		super("You have specified an invalid number of command line " +
			"arguments" +
			"\nUsage: java buildAndHeapifyTree/HeapDriver <graph_file>");
	}
}
