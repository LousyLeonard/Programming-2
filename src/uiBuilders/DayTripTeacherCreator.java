package uiBuilders;

import java.util.Map;

import core.IAddTreeDialog;
import core.IUIBuilderCreator;
import core.UIBuilder;
import core.ui.DialogBuilder;
import dialogs.ClassSelectorDialogCreator;
import dialogs.TripDayTeacherOrganisedDialogCreator;
import plugins.PaymentPlugin;
import plugins.PhoneNumberPlugin;
import plugins.TransportPlugin;
import plugins.TripFeePlugin;
import plugins.VenueBookingPlugin;
import transports.Transportable;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;
import venueBookings.VenueBookingable;

/**
*
* @author Lawrence
*/
public class DayTripTeacherCreator implements IUIBuilderCreator, Trip {

	private static final String TYPE = "Day Trip Teacher Organised";

	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		return getTripDayTeacherOrganisedObject(
				(String)entries.get(TripTrackerConstants.TITLE), 
				(Double)entries.get(TripTrackerConstants.ENTRY_FEE), 
				(Transportable)entries.get(TripTrackerConstants.TRANSPORT),
				(VenueBookingable)entries.get(TripTrackerConstants.VENUE_BOOKING));
	}

	private static UIBuilder<Student> getTripDayTeacherOrganisedObject(String title, Double entryFee, Transportable transport, VenueBookingable booking) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), TripTrackerConstants.PAYMENTS));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	trip.addLabelPlugin(new VenueBookingPlugin(booking));
    	
    	trip.registerAddDialog(new ClassSelectorDialogCreator());

		return trip; 
	}
	
	@Override
	public DialogBuilder getAddDialog(IAddTreeDialog addable) {
		return new TripDayTeacherOrganisedDialogCreator().getNewInstance(addable);
	}
	
	@Override
	public String toString() {
		return TYPE;
	}
	
}
