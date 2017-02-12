package plugins;

import java.util.HashMap;

import core.ILabelPlugin;
import transports.Transportable;
import venueBookings.VenueBookingable;

public class VenueBookingPlugin implements ILabelPlugin {

	private static String PLUGIN_TITLE = "Venue Booking";
	private static String TYPE_TITLE = "Type";
	private static String DEPARTURE_DATE_TITLE = "Departure Date";
	private static String ARRIVAL_DATE_TITLE = "Arrival Date";
	private static String LENGTH_OF_STAY = "Length of Stay";
	
	private VenueBookingable booking;
	
	public VenueBookingPlugin(VenueBookingable booking) {
		this.booking = booking;
	}
	
	@Override
	public String getTitle() {
		return PLUGIN_TITLE;
	}

	@Override
	public HashMap<String, String> getInfo() {
		HashMap<String, String> infoBoxes = new HashMap<String, String>();
		
		infoBoxes.put(TYPE_TITLE, booking.getType());
		infoBoxes.put(DEPARTURE_DATE_TITLE, booking.getDepartureDate().toString());
		infoBoxes.put(ARRIVAL_DATE_TITLE, booking.getArrivalDate().toString());
		infoBoxes.put(LENGTH_OF_STAY, booking.getLengthOfStay());

		return infoBoxes;
	}

}
