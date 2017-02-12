package venueBookings;

import java.util.Date;

import core.IEntryPanelProvider;

public interface VenueBookingable extends IEntryPanelProvider {

	public String getType();
	public Date getArrivalDate();
	public void setArrivalDate(Date arrDate);
	public Date getDepartureDate();
	public void setDepartureDate(Date depdate);
	public String getLengthOfStay();

}
