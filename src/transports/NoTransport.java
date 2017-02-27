package transports;

import java.io.Serializable;

import javax.swing.JPanel;

/**
* A representation of no transport.
* 
* @author Lawrence
*/
public class NoTransport implements ITransport, Serializable {

	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -4169004375886307L;

	/* (non-Javadoc)
	 * @see transports.ITransport#getType()
	 */
	@Override
	public String getType() {
		return "N/A";
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#getDepartureTime()
	 */
	@Override
	public String getDepartureTime() {
		return "N/A";
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#getArrivalTime()
	 */
	@Override
	public String getArrivalTime() {
		return "N/A";
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#setDepartureTime(java.lang.String)
	 */
	@Override
	public void setDepartureTime(String departureTime) {
		// Intentionally blank.
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#setArrivalTime(java.lang.String)
	 */
	@Override
	public void setArrivalTime(String arrivalTime) {
		// Intentionally blank.
		
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getTitle()
	 */
	@Override
	public String getTitle() {
		return getType();
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
		return "No Transport";
	}

}
