package plugins;

import java.io.Serializable;
import java.util.HashMap;

import core.ILabelPlugin;
import venueBookings.IVenueBooking;

/**
* A Label representing the Venue Booking.
* 
* @author Lawrence
*/
public class VenueBookingPlugin implements ILabelPlugin, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1614593008762956609L;
	
	private static String PLUGIN_TITLE = "Venue Booking";
	private static String TYPE_TITLE = "Type";
	private static String DEPARTURE_DATE_TITLE = "Departure Date";
	private static String ARRIVAL_DATE_TITLE = "Arrival Date";
	private static String LENGTH_OF_STAY = "Length of Stay";
	
	private IVenueBooking booking;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param booking - The VenueBooking to represent.
	 */
	public VenueBookingPlugin(IVenueBooking booking) {
		this.booking = booking;
	}
	
	/* (non-Javadoc)
	 * @see core.ILabelPlugin#getTitle()
	 */
	@Override
	public String getTitle() {
		return PLUGIN_TITLE;
	}

	/* (non-Javadoc)
	 * @see core.ILabelPlugin#getInfo()
	 */
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
