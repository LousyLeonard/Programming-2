package core.events;

import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
*
* @author Lawrence
*/
public class HideWindowEvent implements IYesNoEvent {

	@Override
	public void doEvent(DialogBuilder builder) {
		builder.setVisible(false);	
	}

}
