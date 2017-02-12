package venueBookings;

import java.util.Date;

import javax.swing.JPanel;

public class NoVenue implements VenueBookingable {

	private static String TYPE = "No Venue Booking";
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public Date getArrivalDate() {
		return new Date();
	}

	@Override
	public Date getDepartureDate() {
		return new Date();
	}

	@Override
	public String getTitle() {
		return TYPE;
	}

	@Override
	public Object getContent() {
		return this;
	}

	@Override
	public JPanel getPanel() {
		return new JPanel();
	}
	
	@Override
	public String toString() {
		return TYPE;
	}

	@Override
	public void setArrivalDate(Date arrDate) {
		//Intentionally Blank
	}

	@Override
	public void setDepartureDate(Date depdate) {
		//Intentionally Blank	
	}

	@Override
	public String getLengthOfStay() {
		return "";
	}

}