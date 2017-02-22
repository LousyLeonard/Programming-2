/**
 * 
 */
package dialogs;

import java.io.Serializable;

import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.events.AddTreeEvent;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericStringEntryPanel;
import tripTracker.TripTrackerConstants;
import uiBuilders.StudentClassCreator;

/**
 * @author Lawrence
 *
 */
public class AddClassDialogCreator implements IDialogCreator, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1234453925078909445L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getAddClassDialog((IAddTreeDialog)addable);
	}

	private static DialogBuilder getAddClassDialog(IAddTreeDialog addable) {
		GenericStringEntryPanel selections = new GenericStringEntryPanel(TripTrackerConstants.NEW_CLASS);

		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_CLASS);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddTreeEvent(addable, new StudentClassCreator(), TripTrackerConstants.CLASSES));

		return builder;
	}
}
