package errors;

/**
 * Indicates that an invalid number of commands line arguments was specified.
 * 
 * @author Joshua Sims
 * @version 09 December 2016
 */
public final class InvalidNumOfCmdLineArgsException extends Exception
{
	/**
	 * Initializes this exception with an appropriate error message.
	 * 
	 * @param usageMessage - the usage message for the program
	 */
	public InvalidNumOfCmdLineArgsException(String usageMessage)
	{
		super("You have specified an invalid number of command line " +
			"arguments" +
			"\n" + usageMessage);
	}
}
