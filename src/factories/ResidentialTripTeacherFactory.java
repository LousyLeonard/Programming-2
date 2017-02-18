package factories;

import java.util.Map;

import core.IAddTreeDialog;
import core.IUIBuilderFactory;
import core.UIBuilder;
import core.plugins.EnumComboPlugin;
import core.ui.DialogBuilder;
import plugins.OptionsEnum;
import plugins.PaymentPlugin;
import plugins.PhoneNumberPlugin;
import plugins.TransportPlugin;
import plugins.TripFeePlugin;
import plugins.VenueBookingPlugin;
import transports.Transportable;
import tripTracker.DialogFactory;
import tripTracker.StringConstants;
import tripTracker.Student;
import venueBookings.VenueBookingable;

/**
*
* @author Lawrence
*/
public class ResidentialTripTeacherFactory implements IUIBuilderFactory, Trip {

	private static final String TYPE = "Residential Trip Teacher Organised";
	
	@Override
	public UIBuilder getNewInstance(Map<String, Object> entries) {
		return getTripResidentialTeacherOrganisedObject(
				(String)entries.get(StringConstants.TITLE), 
				(Double)entries.get(StringConstants.ENTRY_FEE), 
				(Transportable)entries.get(StringConstants.TRANSPORT),
				(VenueBookingable)entries.get(StringConstants.VENUE_BOOKING));
	}

	private static UIBuilder<Student> getTripResidentialTeacherOrganisedObject(String title, Double entryFee, Transportable transport, VenueBookingable booking) {
    	UIBuilder<Student> trip = new UIBuilder<Student>(title, StringConstants.STUDENTS);
    	
    	trip.addPlugin(new PhoneNumberPlugin<Student>(trip.getPrimaryKeyList()));
    	trip.addPlugin(new PaymentPlugin<Student>(trip.getPrimaryKeyList(), StringConstants.PAYMENTS));
    	trip.addPlugin(new EnumComboPlugin<OptionsEnum, Student>(
    			OptionsEnum.class, OptionsEnum.NOT_YET, trip.getPrimaryKeyList(), StringConstants.AUTHORISATION));
    	
    	trip.addLabelPlugin(new TripFeePlugin(entryFee));
    	trip.addLabelPlugin(new TransportPlugin(transport));
    	trip.addLabelPlugin(new VenueBookingPlugin(booking));
    	
    	trip.registerAddDialog(DialogFactory.getClassSelectorDialog(trip));

		return trip; 
	}

	@Override
	public DialogBuilder getAddDialog(IAddTreeDialog addable) {
		return DialogFactory.getTripResidentialTeacherOrganisedDialog(addable);
	}
	
	@Override
	public String toString() {
		return TYPE;
	}
}
