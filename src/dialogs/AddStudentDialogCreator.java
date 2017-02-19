/**
 * 
 */
package dialogs;

import java.io.Serializable;

import core.IAddDialog;
import core.IDialogCreator;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericStringEntryPanel;
import events.AddStudentEvent;
import tripTracker.StringConstants;

/**
 * @author Lawrence
 *
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
	
	private static DialogBuilder getAddStudentDialog(IAddDialog addable) {
		GenericStringEntryPanel firstName = new GenericStringEntryPanel(StringConstants.FIRST_NAME);
		GenericStringEntryPanel secondName = new GenericStringEntryPanel(StringConstants.SECOND_NAME);
		GenericStringEntryPanel phoneNumber = new GenericStringEntryPanel(StringConstants.PHONE_NUMBER);

		DialogBuilder builder = new DialogBuilder(StringConstants.NEW_STUDENT);
		
		builder.addPanel(firstName);
		builder.addPanel(secondName);
		builder.addPanel(phoneNumber);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddStudentEvent(addable));
		
		return builder;
	}

}
