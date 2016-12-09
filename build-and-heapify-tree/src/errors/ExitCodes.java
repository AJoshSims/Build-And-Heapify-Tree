package errors;

/**
 * Repository for exit codes relevant to the "Build and Heapify Tree" project.
 * 
 * @author Joshua Sims
 * @version 09 December 2016
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
	
	/**
	 * Indicates that the content of the specified input file, or the format 
	 * of that content, is invalid.
	 */
	public static final int INVALID_INPUT_FILE = 3;
}