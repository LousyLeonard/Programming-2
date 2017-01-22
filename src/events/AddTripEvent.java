package events;

import java.util.Map;

import javax.swing.JOptionPane;

import core.IAddDialog;
import core.NotUniqueEntryException;
import core.UIBuilder;
import core.IYesNoEvent;
import core.ui.DialogBuilder;
import tripTracker.StringConstants;
import tripTracker.NotATripTypeException;
import tripTracker.TripFactory;
import tripTracker.TripTypes;

public class AddTripEvent implements IYesNoEvent {

	private IAddDialog addable;
	private TripTypes tripType;

	public AddTripEvent(IAddDialog addable, TripTypes tripType) {
		this.addable = addable;
		this.tripType = tripType;
	}
	
	@Override
	public void doEvent(DialogBuilder builder) {
		try {
			addable.addEntry(parse(builder.getEntrys()).getPanel());
			
			// Get rid of the entry dialog.
			builder.dispose();
		} catch (NotUniqueEntryException e) {
			JOptionPane.showMessageDialog(null,
				    "An entry by this name already exists.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);		
		}
	}
	
	private UIBuilder parse(Map<String, Object> entries) {
		return TripFactory.parse(tripType, entries);
	}

}
