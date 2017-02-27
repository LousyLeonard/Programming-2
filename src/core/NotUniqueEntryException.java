package core;

/**
* Exception to be thrown in the case where there already exists a primary key
* entry of given details in the UIBuilder.
* 
* @author Lawrence
*/
public class NotUniqueEntryException extends Exception {

	/**
	 * The error message to show.
	 */
	private static String ERROR_MESSAGE = "This entry is not unique";
	
	/**
	 * CONSTRUCTOR
	 */
	public NotUniqueEntryException() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
