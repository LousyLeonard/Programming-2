/**
 * 
 */
package core.dialogs;

import core.CoreConstants;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.events.GetElementTypeEvent;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;

/**
 * @author Lawrence
 *
 */
public class ElementTypeDialogCreator implements IDialogCreator {
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getElementTypeDialog((IAddTreeDialog)addable);
	}
	
	private static DialogBuilder getElementTypeDialog(IAddTreeDialog addable) {
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(
				CoreConstants.ELEMENT_TYPE, addable.getTopLevelElements());

		DialogBuilder builder = new DialogBuilder(CoreConstants.NEW_ELEMENT);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new GetElementTypeEvent(addable));

		return builder;
	}

}
