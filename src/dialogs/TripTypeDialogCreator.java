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
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericExclusiveSelectionPanel;
import events.GetTripTypeEvent;
import tripTracker.TripTrackerConstants;
import uiBuilders.Trip;

/**
 * @author Lawrence
 *
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
		// TODO Auto-generated method stub
		return getTripTypeDialog((IAddTreeDialog)addable);
	}
	
	private static DialogBuilder getTripTypeDialog(IAddTreeDialog addable) {
		CustomClassLoader<Trip> tripLoader = new CustomClassLoader<Trip>(Trip.class);
		ArrayList<Trip> trips = tripLoader.getElements();		
		GenericExclusiveSelectionPanel selections = new GenericExclusiveSelectionPanel(TripTrackerConstants.TRIP_TYPE, trips);

		DialogBuilder builder = new DialogBuilder(TripTrackerConstants.NEW_TRIP);		
		
		builder.addPanel(selections);
		
		builder.registerNoEvent(new HideWindowEvent());
		builder.registerYesEvent(new GetTripTypeEvent(addable));

		return builder;
	}
}
