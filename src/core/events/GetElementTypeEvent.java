package core.events;

import java.util.Map;

import core.CoreConstants;
import core.IAddTreeDialog;
import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
*
* @author Lawrence
*/
public class GetElementTypeEvent implements IYesNoEvent {
	
	private IAddTreeDialog addable;
	
	public GetElementTypeEvent(IAddTreeDialog addable) {
		this.addable = addable;
	}

	@Override
	public void doEvent(DialogBuilder builder) {
		Map<String, Object> entries = builder.getEntrys();
		String choice = (String)entries.get(CoreConstants.ELEMENT_TYPE);

		for (Object element : addable.getTopLevelElements()) {
			if(choice.equals((String)element)) {
				DialogBuilder dialog = addable.getAddDialogForString(choice);
				dialog.setVisible(true);
				builder.setVisible(false);
			}
		}
	}

}
