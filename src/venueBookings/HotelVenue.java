/**
 * 
 */
package venueBookings;

import java.io.Serializable;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.ui.entrypanels.GenericStringEntryPanel;

/**
 * A VenueBooking representing a Hotel Venue.
 * 
 * @author Lawrence
 */
public class HotelVenue implements IVenueBooking, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -1878446451628023625L;
	
	private static String TYPE = "Hotel Booking";
	private static String LENGTH_OF_STAY_LABEL = "Length of Stay";
	private static String ARR_DATE_LABEL = "Arrival Date";
	private static String DEP_DATE_LABEL = "Departure Date";
		
	private GenericStringEntryPanel lengthOfStayPanel;
	private GenericStringEntryPanel arrDatePanel;
	private GenericStringEntryPanel depDatePanel;

	private String lengthOfStay;
	private Date arrDate;
	private Date depdate;
	
	/**
	 * CONSTRUCTOR
	 */
	public HotelVenue() {
		lengthOfStay = new String();
		arrDate = new Date();
		depdate = new Date();
		
		lengthOfStayPanel = new GenericStringEntryPanel(LENGTH_OF_STAY_LABEL);
		arrDatePanel = new GenericStringEntryPanel(ARR_DATE_LABEL);
		depDatePanel = new GenericStringEntryPanel(DEP_DATE_LABEL);
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
		this.lengthOfStay = (String)lengthOfStayPanel.getContent();
		this.arrDate = new Date();
		this.depdate = new Date();
		
		return this;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		JPanel hotelPanel = new JPanel();
		BoxLayout layout = new BoxLayout(hotelPanel, BoxLayout.PAGE_AXIS);
		lengthOfStayPanel = new GenericStringEntryPanel(LENGTH_OF_STAY_LABEL);
		arrDatePanel = new GenericStringEntryPanel(ARR_DATE_LABEL);
		depDatePanel = new GenericStringEntryPanel(DEP_DATE_LABEL);
		hotelPanel.setLayout(layout);
		hotelPanel.add(lengthOfStayPanel);
		hotelPanel.add(arrDatePanel);
		hotelPanel.add(depDatePanel);
		return hotelPanel;
	}

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
	public Date getArrivalDate() {
		return new Date();
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getDepartureDate()
	 */
	@Override
	public Date getDepartureDate() {
		return new Date();
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#setArrivalDate(java.util.Date)
	 */
	@Override
	public void setArrivalDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#setDepartureDate(java.util.Date)
	 */
	@Override
	public void setDepartureDate(Date depdate) {
		this.depdate = depdate;
	}

	/* (non-Javadoc)
	 * @see venueBookings.IVenueBooking#getLengthOfStay()
	 */
	@Override
	public String getLengthOfStay() {
		return lengthOfStay;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return TYPE;
	}


}
