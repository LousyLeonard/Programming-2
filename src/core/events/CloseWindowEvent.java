package core.events;

import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
* Closes the given DialogBuilder instance.
*
* @author Lawrence
*/
public class CloseWindowEvent implements IYesNoEvent {

	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		builder.dispose();	
	}

}
