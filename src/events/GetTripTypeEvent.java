package events;

import java.util.Map;

import core.IAddTreeDialog;
import core.IYesNoEvent;
import core.ui.DialogBuilder;
import tripTracker.TripTrackerConstants;
import uiBuilders.ITrip;

/**
* Event to create a trip entry dialog from a trip type seleection.
* 
* @author Lawrence
*/
public class GetTripTypeEvent implements IYesNoEvent {

	private IAddTreeDialog addable;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param addable - The element to add to.
	 */
	public GetTripTypeEvent(IAddTreeDialog addable) {
		this.addable = addable;
	}
	
	/* (non-Javadoc)
	 * @see core.IYesNoEvent#doEvent(core.ui.DialogBuilder)
	 */
	@Override
	public void doEvent(DialogBuilder builder) {
		Map<String, Object> entries = builder.getEntrys();
		ITrip tripType = (ITrip)entries.get(TripTrackerConstants.TRIP_TYPE);
		
		// Handles no trip selected
		if (tripType != null) {
					
			DialogBuilder tripEntryDialog;
			tripEntryDialog = tripType.getAddDialog(addable);
			tripEntryDialog.setVisible(true);
			builder.setVisible(false);
		}
	}

}
