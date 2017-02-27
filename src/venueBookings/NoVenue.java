package venueBookings;

import java.io.Serializable;
import java.util.Date;

import javax.swing.JPanel;

/**
* A VenueBooking representing no venue booking.
*
* @author Lawrence
*/
public class NoVenue implements IVenueBooking, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1960870542684646614L;
	
	private static String TYPE = "No Venue Booking";
	
	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getType()
	 */
	@Override
	public String getType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getArrivalDate()
	 */
	@Override
	public String getArrivalDate() {
		return "N/A";
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getDepartureDate()
	 */
	@Override
	public String getDepartureDate() {
		return "N/A";
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getTitle()
	 */
	@Override
	public String getTitle() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getContent()
	 */
	@Override
	public Object getContent() {
		return this;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		return new JPanel();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#setArrivalDate(java.util.Date)
	 */
	@Override
	public void setArrivalDate(String arrDate) {
		//Intentionally Blank
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#setDepartureDate(java.util.Date)
	 */
	@Override
	public void setDepartureDate(String depdate) {
		//Intentionally Blank	
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getLengthOfStay()
	 */
	@Override
	public String getLengthOfStay() {
		return "N/A";
	}

}
