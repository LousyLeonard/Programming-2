package core.events;

import java.util.Map;

import core.IAddTreeDialog;
import core.IYesNoEvent;
import core.ui.DialogBuilder;
import core.ui.TreeNavigator;

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
		String choice = (String)entries.get(TreeNavigator.ELEMENT_TYPE);

		for (Object element : addable.getTopLevelElements()) {
			if(choice.equals((String)element)) {
				DialogBuilder dialog = addable.getAddDialogForString(choice);
				dialog.setVisible(true);
				builder.setVisible(false);
			}
		}
	}

}
