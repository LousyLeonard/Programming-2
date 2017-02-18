/**
 * 
 */
package events;

import core.IAddDialog;
import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
 * @author Lawrence
 *
 */
public class GetClassEvent implements IYesNoEvent {
	
	private IAddDialog addable;

	public GetClassEvent(IAddDialog addable) {
		this.addable = addable;
	}

	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		// TODO Auto-generated method stub

	}

}
