package errors;

/**
 * Indicates that the content of the specified input file, or the format of 
 * that content, is invalid.
 * 
 * @author Joshua Sims
 * @version 09 December 2016
 */
public class InvalidInputFileException extends Exception
{
	/**
	 * Initializes this exception with an appropriate error message.
	 * 
	 * @param correctContent - an example of the correct content
	 */
	public InvalidInputFileException(String correctContent)
	{
		super("The content of the specified input file, or the format of " +
			"that content, is invalid. " +
			"The following is an example of correct content" +
			"\n" + correctContent);
	}
}
