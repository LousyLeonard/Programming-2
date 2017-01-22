package core;

public class NotUniqueEntryException extends Exception {

	private static String ERROR_MESSAGE = "This entry is not unique";
	
	public NotUniqueEntryException() {
		super();
	}
	
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
