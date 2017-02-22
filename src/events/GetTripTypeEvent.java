package events;

import java.util.Map;

import core.IAddTreeDialog;
import core.IYesNoEvent;
import core.ui.DialogBuilder;
import tripTracker.TripTrackerConstants;
import uiBuilders.Trip;

/**
*
* @author Lawrence
*/
public class GetTripTypeEvent implements IYesNoEvent {

	private IAddTreeDialog addable;
	
	public GetTripTypeEvent(IAddTreeDialog addable) {
		this.addable = addable;
	}
	
	@Override
	public void doEvent(DialogBuilder builder) {
		Map<String, Object> entries = builder.getEntrys();
		Trip tripType = (Trip)entries.get(TripTrackerConstants.TRIP_TYPE);
		
		// Handles no trip selected
		if (tripType != null) {
					
			DialogBuilder tripEntryDialog;
			tripEntryDialog = tripType.getAddDialog(addable);
			tripEntryDialog.setVisible(true);
			builder.setVisible(false);
		}
	}

}
