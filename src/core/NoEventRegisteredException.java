package core;

/**
* Exception to be thrown in the case where an accept/reject command is selected
* on a DialogBuilder but there is no associated YesNoEvent to handle the action.
* 
* @author Lawrence
*/
public class NoEventRegisteredException extends Exception {

	/**
	 * The error message to show.
	 */
	private static String ERROR_MESSAGE = "No event registered for button";
	
	/**
	 * CONSTRUCTOR
	 */
	public NoEventRegisteredException() {
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
