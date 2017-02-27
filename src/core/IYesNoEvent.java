package core;

import core.ui.DialogBuilder;

/**
* An event interface for defining the behaviours of the
* confirmation and cancellation buttons of a DialogBuiler.
* 
* @author Lawrence
*/
public interface IYesNoEvent {

	/**
	 * The behaviour to perform for the given event.
	 * 
	 * @param builder - A handle to the builder object and its content.
	 */
	public void doEvent(DialogBuilder builder);
}
