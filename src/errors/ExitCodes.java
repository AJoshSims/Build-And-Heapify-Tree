package errors;

/**
 * Repository for exit codes relevant to the "Build and Heapify Tree" project.
 * 
 * @author Joshua Sims
 * @version 09 October, 2016
 */
public final class ExitCodes
{
	/**
	 * Indicates that an invalid number of commands line arguments were 
	 * passed.
	 */
	public static final int INVALID_NUM_OF_CMD_LINE_ARGS = 1;
	
	/**
	 * Indicates that the input file, specified by the first command line 
	 * argument, could not be found.
	 */
	public static final int FILE_NOT_FOUND = 2;
}
