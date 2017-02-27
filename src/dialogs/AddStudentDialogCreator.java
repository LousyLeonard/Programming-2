/**
 * 
 */
package dialogs;

import java.io.Serializable;

import core.IAddDialog;
import core.IDialogCreator;
import core.events.CloseWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericStringEntryPanel;
import events.AddStudentEvent;
import tripTracker.TripTrackerConstants;

/**
 * A Dialog creator for adding a student to a IAddDialog.
 *  
 * @author Lawrence
 */
public class AddStudentDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 357591469580201152L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getAddStudentDialog(addable);
	}
	
	/**
	 * Create a new add student dialog.
	 * 
	 * @param addable - The element to add to.
	 * @return the add new student DialogBuilder.
	 */
	private static DialogBuilder getAddStudentDialog(IAddDialog addable) {
		GenericStringEntryPanel firstName = new GenericStringEntryPanel(TripTrackerConstants.FIRST_NAME);
		GenericStringEntryPanel secondName = new GenericStringEntryPanel(TripTrackerConstants.SECOND_NAME);
		GenericStringEntryPanel phoneNumber = new GenericStringEntryPanel(TripTrackerConstants.PHONE_NUMBER);

		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_STUDENT);
		
		builder.addPanel(firstName);
		builder.addPanel(secondName);
		builder.addPanel(phoneNumber);
		
		builder.registerNoEvent(new CloseWindowEvent());
		builder.registerYesEvent(new AddStudentEvent(addable));
		
		return builder;
	}

}
