package events;

import java.util.Map;

import javax.swing.JOptionPane;

import core.IAddDialog;
import core.NotUniqueEntryException;
import core.UIBuilder;
import core.IYesNoEvent;
import core.ui.DialogBuilder;
import transports.NoTransport;
import tripTracker.DialogFactory;
import tripTracker.NotATripTypeException;
import tripTracker.Student;
import tripTracker.TripFactory;
import tripTracker.StringConstants;
import tripTracker.TripTypes;

public class GetTripTypeEvent implements IYesNoEvent {

	private IAddDialog addable;
	
	public GetTripTypeEvent(IAddDialog addable) {
		this.addable = addable;
	}
	
	@Override
	public void doEvent(DialogBuilder builder) {
		Map<String, Object> entries = builder.getEntrys();
		TripTypes tripType = (TripTypes)entries.get(StringConstants.TRIP_TYPE);
		
		// Handles no trip selected
		if (tripType != null) {
					
			DialogBuilder tripEntryDialog;
			try {
				tripEntryDialog = DialogFactory.parse(addable, tripType);
				tripEntryDialog.setVisible(true);
				builder.setVisible(false);
			} catch (NotATripTypeException e) {
				e.printStackTrace();
			}
		}
	}

}
