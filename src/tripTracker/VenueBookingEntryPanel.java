package tripTracker;

import javax.swing.JPanel;

import core.IEntryPanelProvider;
import transports.NoTransport;
import venueBookings.NoBooking;

public class VenueBookingEntryPanel implements IEntryPanelProvider {

	private String title;
	
	public VenueBookingEntryPanel(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Object getContent() {
		return new NoBooking();
	}

	@Override
	public JPanel getPanel() {
		return new JPanel();
	}

}
