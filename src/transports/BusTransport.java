package transports;

import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.ui.entrypanels.GenericStringEntryPanel;

/**
* A transport representing a bus.
* 
* @author Lawrence
*/
public class BusTransport implements ITransport, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -2193386438643529730L;
	
	private final static String TYPE = "Bus";
	private final static String DEPARTURE_TIME = "Departure Time";
	private final static String ARRIVAL_TIME = "Arrival Time";

	private String departureTime;
	private String arrivalTime;
	
	private GenericStringEntryPanel depPanel;
	private GenericStringEntryPanel arrPanel;
	
	//TODO
//	private Date departureTime;
//	private Date arrivalTime;
	
	/**
	 * CONSTRUCTOR
	 */
	public BusTransport() {
		this.departureTime = "";
		this.arrivalTime = "";
		
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
	}
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param departureTime - The departure time.
	 * @param arrivalTime - The arrival time.
	 */
	public BusTransport(String departureTime, String arrivalTime) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#getType()
	 */
	@Override
	public String getType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#getDepartureTime()
	 */
	@Override
	public String getDepartureTime() {
		//TODO
//		SimpleDateFormat ft = 
//			      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//
//		return ft.format(departureTime);
		return departureTime;

	}

	/* (non-Javadoc)
	 * @see transports.ITransport#getArrivalTime()
	 */
	@Override
	public String getArrivalTime() {
		return arrivalTime;
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#setDepartureTime(java.lang.String)
	 */
	@Override
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;		
	}

	/* (non-Javadoc)
	 * @see transports.ITransport#setArrivalTime(java.lang.String)
	 */
	@Override
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
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
		setDepartureTime((String)depPanel.getContent());
		setArrivalTime((String)arrPanel.getContent());
		
		return this;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		JPanel busPanel = new JPanel();
		BoxLayout layout = new BoxLayout(busPanel, BoxLayout.PAGE_AXIS);
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
		busPanel.setLayout(layout);
		busPanel.add(depPanel);
		busPanel.add(arrPanel);
		return busPanel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return TYPE;
	}

}