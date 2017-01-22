package core;

public class NoDialogRegisteredException extends Exception {

	private static String ERROR_MESSAGE = "No Dialog registered for add button";
	
	public NoDialogRegisteredException() {
		super();
	}
	
	public String getMessage() {
		return ERROR_MESSAGE;
	}

}
