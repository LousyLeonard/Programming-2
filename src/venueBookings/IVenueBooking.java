package venueBookings;

import java.util.Date;

import core.IEntryPanelProvider;

/**
* A definition of the behaviour of a VenueBooking.
* 
* @author Lawrence
*/
public interface IVenueBooking extends IEntryPanelProvider {

	/**
	 * Get the type of the VenueBooking.
	 * 
	 * @return the type of the VenueBooking.
	 */
	public String getType();
	
	/**
	 * Get the arrival date of the venue booking.
	 * 
	 * @return the arrival date.
	 */
	public Date getArrivalDate();
	
	/**
	 * Set the arrival date of the venue booking.
	 * 
	 * @param arrDate - The arrival date.
	 */
	public void setArrivalDate(Date arrDate);
	
	/**
	 * Get the departure date of the venue booking.
	 * 
	 * @return the departure date.
	 */
	public Date getDepartureDate();
	
	/**
	 * Set the departure date of the venue booking.
	 * 
	 * @param arrDate - The departure date.
	 */
	public void setDepartureDate(Date depdate);
	
	/**
	 * Get the length of the stay.
	 * 
	 * @return the length of the stay.
	 */
	public String getLengthOfStay();

}
