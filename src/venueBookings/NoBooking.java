package venueBookings;

import java.util.Date;

import tripTracker.VenueBookingable;

public class NoBooking implements VenueBookingable {

	@Override
	public String getType() {
		return "N/A";
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
	public String getLengthofStay() {
		return "N/A";
	}

}
