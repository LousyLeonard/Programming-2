package core;

/**
* Exception to be thrown in the case where an add command is requested
* on an IAddDialog but there is no associated DialogBuilder from which to
* populate the new element.
*
* @author Lawrence
*/
public class NoDialogRegisteredException extends Exception {

	/**
	 * The error message to show.
	 */
	private static String ERROR_MESSAGE = "No Dialog registered for add button";
	
	/**
	 * CONSTRUCTOR
	 */
	public NoDialogRegisteredException() {
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
