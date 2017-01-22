package core;

public class NoEventRegisteredException extends Exception {

	private static String ERROR_MESSAGE = "No event registered for button";
	
	public NoEventRegisteredException() {
		super();
	}
	
	public String getMessage() {
		return ERROR_MESSAGE;
	}

}
