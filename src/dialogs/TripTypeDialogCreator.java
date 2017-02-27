/**
 * 
 */
package dialogs;

import java.io.Serializable;
import java.util.ArrayList;

import core.CustomClassLoader;
import core.IAddDialog;
import core.IAddTreeDialog;
import core.IDialogCreator;
import core.events.CloseWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import events.GetTripTypeEvent;
import tripTracker.TripTrackerConstants;
import uiBuilders.ITrip;

/**
 * A Trip type selector for selecting a trip type for adding to an IAddTreeDialog.
 *  
 * @author Lawrence
 */
public class TripTypeDialogCreator implements IDialogCreator, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 7656607085601207282L;
	
	/* (non-Javadoc)
	 * @see core.IDialogCreator#getNewInstance(core.IAddDialog)
	 */
	@Override
	public DialogBuilder getNewInstance(IAddDialog addable) {
		return getTripTypeDialog((IAddTreeDialog)addable);
	}
	
	/**
	 * Create a new trip dialog.
	 * 
	 * @param addable - The element to add to.
	 * @return the new trip type selector DialogBuilder.
	 */
	private static DialogBuilder getTripTypeDialog(IAddTreeDialog addable) {
		CustomClassLoader<ITrip> tripLoader = new CustomClassLoader<ITrip>(ITrip.class);
		ArrayList<ITrip> trips = tripLoader.getElements();		
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(TripTrackerConstants.TRIP_TYPE, trips);

		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_TRIP);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new CloseWindowEvent());
		builder.registerYesEvent(new GetTripTypeEvent(addable));

		return builder;
	}
}
