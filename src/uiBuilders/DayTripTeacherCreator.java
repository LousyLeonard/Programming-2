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
import transports.ITransport;
import tripTracker.TripTrackerConstants;
import tripTracker.Student;
import venueBookings.IVenueBooking;

/**
* A Trip creator for the Day Trip provided by a Teacher trip type.
*
* @author Lawrence
*/
public class DayTripTeacherCreator implements IUIBuilderCreator, ITrip {

	private static final String TYPE = "Day Trip Teacher Organised";

	/* (non-Javadoc)
	 * @see core.IUIBuilderCreator#getNewInstance(java.util.Map)
	 */
	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		return getTripDayTeacherOrganisedObject(
				(String)entries.get(TripTrackerConstants.TITLE), 
				(Double)entries.get(TripTrackerConstants.ENTRY_FEE), 
				(ITransport)entries.get(TripTrackerConstants.TRANSPORT),
				(IVenueBooking)entries.get(TripTrackerConstants.VENUE_BOOKING));
	}

	/**
	 * Create a Day Trip provided by a Teacher trip instance.
	 * 
	 * @param title - The title of the trip.
	 * @param entryFee - The entry fee.
	 * @param transport - The transport.
	 * @param booking - The Venue Booking.
	 * @return a new instance of a trip.
	 */
	private static UIBuilder<Student> getTripDayTeacherOrganisedObject(String title, Double entryFee, ITransport transport, IVenueBooking booking) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, TripTrackerConstants.STUDENTS);
    	
    	trip.addColumnPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addColumnPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), TripTrackerConstants.PAYMENTS));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	trip.addLabelPlugin(new VenueBookingPlugin(booking));
    	
    	trip.registerAddDialog(new ClassSelectorDialogCreator());

		return trip; 
	}
	
	/* (non-Javadoc)
	 * @see uiBuilders.ITrip#getAddDialog(core.IAddTreeDialog)
	 */
	@Override
	public DialogBuilder getAddDialog(IAddTreeDialog addable) {
		return new TripDayTeacherOrganisedDialogCreator().getNewInstance(addable);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return TYPE;
	}
	
}
