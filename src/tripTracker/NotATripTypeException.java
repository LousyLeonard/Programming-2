package tripTracker;

/**
*
* @author Lawrence
*/
public class NotATripTypeException extends Exception {

	private static String ERROR_MESSAGE = "An unrecognised trip type was selected";
	
	public NotATripTypeException() {
		super();
	}
	
	public String getMessage() {
		return ERROR_MESSAGE;
	}

}
