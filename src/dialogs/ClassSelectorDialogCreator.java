/**
 * 
 */
package dialogs;

import java.io.Serializable;

import core.IAddDialog;
import core.IDialogCreator;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import events.AddClassEvent;
import tripTracker.ClassManager;
import tripTracker.TripTrackerConstants;

/**
 * @author Lawrence
 *
 */
public class ClassSelectorDialogCreator implements IDialogCreator, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -9019124990419909568L;

	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getClassSelectorDialog(addable);
	}
	
	private static DialogBuilder getClassSelectorDialog(IAddDialog addable) {
		GenericExclusiveSelectionPanel selections = 
				new GenericExclusiveSelectionPanel(TripTrackerConstants.CLASS, ClassManager.getInstance().getClasses());
		
		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.ADD_CLASS);	
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new AddClassEvent(addable));
				
		return builder;
	}

}
