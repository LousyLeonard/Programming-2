/**
 * 
 */
package core.dialogs;

import core.CoreConstants;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.events.GetElementTypeEvent;
import core.events.CloseWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;

/**
 * Creates a DialogBuilder that lists the top level elements on a 
 * IAddTreeDialog and allows addition to the selected one.
 * 
 * @author Lawrence
 */
public class ElementTypeDialogCreator implements IDialogCreator {
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getElementTypeDialog((IAddTreeDialog)addable);
	}
	
	/**
	 * Creates a DialogBuilder consisting of the top level elements 
	 * in a IAddTreeDialog. A single mutually exclusive checkbox is displayed
	 * for each. 
	 * 
	 * @param addable - The IAddTreeDialog to be added to.
	 * @return a DialogBuilder representing selection of the top level elements.
	 */
	private static DialogBuilder getElementTypeDialog(IAddTreeDialog addable) {
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(
				CoreConstants.ELEMENT_TYPE, addable.getTopLevelElements());

		DialogBuilder builder = new DialogBuilder(CoreConstants.NEW_ELEMENT);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new CloseWindowEvent());
		builder.registerYesEvent(new GetElementTypeEvent(addable));

		return builder;
	}

}
